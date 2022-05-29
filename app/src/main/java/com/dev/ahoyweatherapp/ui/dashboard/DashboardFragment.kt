package com.dev.ahoyweatherapp.ui.dashboard

import android.transition.TransitionInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.ahoyweatherapp.R
import com.dev.ahoyweatherapp.core.BaseFragment
import com.dev.ahoyweatherapp.core.Constants
import com.dev.ahoyweatherapp.databinding.FragmentDashboardBinding
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
import dagger.hilt.android.AndroidEntryPoint
/**
 * Created by Bino on 2022-05-30
 */
@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(
    R.layout.fragment_dashboard,
    DashboardFragmentViewModel::class.java,
) {

    override fun init() {
        super.init()
        initForecastAdapter()
        initFavoriteAdapter()
        viewModel.fetchFavorite()
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(
            android.R.transition.move
        )

        val lat: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LAT, "")
        val lon: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "")
        initFavorite(viewModel.favoriteList)
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

    private fun initForecast(list: List<ListItem>) {
        (binding.recyclerForecast.adapter as ForecastAdapter).submitList(list)
    }
    private fun initFavorite(list: List<FavoriteEntity>) {
        (binding.recyclerFavorite.adapter as FavoriteAdapter).submitList(list)
    }

    override fun onResume() {
        super.onResume()
        initFavorite(viewModel.favoriteList)

    }


}
