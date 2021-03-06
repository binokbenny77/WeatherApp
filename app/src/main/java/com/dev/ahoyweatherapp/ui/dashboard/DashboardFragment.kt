package com.dev.ahoyweatherapp.ui.dashboard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.transition.TransitionInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.ahoyweatherapp.R
import com.dev.ahoyweatherapp.core.BaseFragment
import com.dev.ahoyweatherapp.core.Constants
import com.dev.ahoyweatherapp.databinding.FragmentDashboardBinding
import com.dev.ahoyweatherapp.db.entity.CoordEntity
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.domain.model.ListItem
import com.dev.ahoyweatherapp.domain.usecase.CurrentWeatherUseCase
import com.dev.ahoyweatherapp.domain.usecase.ForecastUseCase
import com.dev.ahoyweatherapp.ui.dashboard.DashboardFragmentDirections.actionDashboardFragmentToWeatherDetailFragment
import com.dev.ahoyweatherapp.ui.dashboard.favorite.FavoriteAdapter
import com.dev.ahoyweatherapp.ui.dashboard.forecast.ForecastAdapter
import com.dev.ahoyweatherapp.ui.main.MainActivity
import com.dev.ahoyweatherapp.utils.extensions.isNetworkAvailable
import com.dev.ahoyweatherapp.utils.extensions.observeWith
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.Listener
import com.google.android.gms.location.LocationRequest
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Bino on 2022-05-30
 */
@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(
    R.layout.fragment_dashboard,
    DashboardFragmentViewModel::class.java,
),Listener {
    var easyWayLocation: EasyWayLocation? = null
    override fun init() {
        super.init()

        initForecastAdapter()
        initFavoriteAdapter()
        viewModel.fetchFavorite()
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(
            android.R.transition.move
        )
        activity?.let {
            val request = LocationRequest();
            request.interval = 10000;
            request.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
            easyWayLocation = EasyWayLocation(it, request, false, false, this)

        }

        val lat: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LAT, "")
        val lon: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "")
        initFavorite(viewModel.favoriteList)

        setupPermissions()
        if (lat?.isNotEmpty() == true && lon?.isNotEmpty() == true) {
            binding.viewModel?.setCurrentWeatherParams(
                CurrentWeatherUseCase.CurrentWeatherParams(
                    lat,
                    lon,
                    isNetworkAvailable(requireContext()),
                    Constants.Coords.METRIC
                )
            )
            binding.viewModel?.setForecastParams(
                ForecastUseCase.ForecastParams(
                    lat,
                    lon,
                    isNetworkAvailable(requireContext()),
                    Constants.Coords.METRIC
                )
            )
        }

//        binding.btFetch.setOnClickListener {
//            viewModel.fetchFavorite()
//        }
        binding.viewModel?.getForecastViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            initFavorite(viewModel.favoriteList)
            with(binding) {
                viewState = it
                it.data?.list?.let { forecasts -> initForecast(forecasts) }
                (activity as MainActivity).viewModel.toolbarTitle.set(
                    it.data?.city?.getCityAndCountry()
                )

            }

        }

        binding.viewModel?.getCurrentWeatherViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                containerForecast.viewState = it
            }
        }



    }

    private fun initForecastAdapter() {
        val adapter = ForecastAdapter { item, cardView, forecastIcon, dayOfWeek, temp, tempMaxMin ->
            val action =
                actionDashboardFragmentToWeatherDetailFragment(
                    item
                )
            findNavController()
                .navigate(
                    action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElements(
                            mapOf(
                                cardView to cardView.transitionName,
                                forecastIcon to forecastIcon.transitionName,
                                dayOfWeek to dayOfWeek.transitionName,
                                temp to temp.transitionName,
                                tempMaxMin to tempMaxMin.transitionName
                            )
                        )
                        .build()
                )
        }

        binding.recyclerForecast.adapter = adapter
        binding.recyclerForecast.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        postponeEnterTransition()
        binding.recyclerForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }
    private fun initFavoriteAdapter() {
        val adapter = FavoriteAdapter { item, cardView, forecastIcon, dayOfWeek, temp, tempMaxMin ->

        }

        binding.recyclerFavorite.adapter = adapter
        binding.recyclerFavorite.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
     //   postponeEnterTransition()
//        binding.recyclerFavorite.viewTreeObserver
//            .addOnPreDrawListener {
//                startPostponedEnterTransition()
//                true
//            }
    }

    private fun setupPermissions() {
        activity?.let {
            val permission = ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

            if (permission != PackageManager.PERMISSION_GRANTED) {
                makeRequest()
            }
        }

    }
    private fun initForecast(list: List<ListItem>) {
        (binding.recyclerForecast.adapter as ForecastAdapter).submitList(list)
    }
    private fun initFavorite(list: List<FavoriteEntity>) {
        (binding.recyclerFavorite.adapter as FavoriteAdapter).submitList(list)
    }

    override fun onResume() {
        super.onResume()
        initFavorite(viewModel.favoriteList)
        //easyWayLocation?.endUpdates()

    }

    override fun onPause() {
        super.onPause()
       // easyWayLocation?.startLocation()
    }

    private fun makeRequest() {

        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        }

    }

    override fun locationOn() {
        Timber.i("location on")
    }

    override fun currentLocation(location: Location?) {
//
//        val lat = location?.latitude.toString()
//        val lon = location?.longitude.toString()
//        if (lat?.isNotEmpty() && lon?.isNotEmpty()) {
//            binding.viewModel?.setCurrentWeatherParams(
//                CurrentWeatherUseCase.CurrentWeatherParams(
//                    lat,
//                    lon,
//                    isNetworkAvailable(requireContext()),
//                    Constants.Coords.METRIC
//                )
//            )
//            binding.viewModel?.setForecastParams(
//                ForecastUseCase.ForecastParams(
//                    lat,
//                    lon,
//                    isNetworkAvailable(requireContext()),
//                    Constants.Coords.METRIC
//                )
//            )
//        }
//        saveCoordsToSharedPref(lat,lon)
      //  easyWayLocation?.endUpdates()
        //easyWayLocation?.startLocation()
    }

    override fun locationCancelled() {



        Timber.i("location Cancelled")
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            EasyWayLocation.LOCATION_SETTING_REQUEST_CODE -> easyWayLocation?.onActivityResult(resultCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> {

                activity?.let {
                    {
                        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                            activity?.let {
                                Toast.makeText(
                                    it,
                                    getString(R.string.permission_denied),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                it,
                                getString(R.string.permission_granted),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

            }
        }
    }


    fun saveCoordsToSharedPref(lat:String,lng:String): Single<String>? {
        return Single.create<String> {
            binding.viewModel?.sharedPreferences?.edit()?.putString(Constants.Coords.LAT, lat)?.apply()
            binding.viewModel?.sharedPreferences?.edit()?.putString(Constants.Coords.LON, lng)?.apply()
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
