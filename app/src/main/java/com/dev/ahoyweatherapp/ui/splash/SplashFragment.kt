package com.dev.ahoyweatherapp.ui.splash

import android.graphics.Color
import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.dev.ahoyweatherapp.R
import com.dev.ahoyweatherapp.core.BaseFragment
import com.dev.ahoyweatherapp.core.Constants
import com.dev.ahoyweatherapp.databinding.FragmentSplashBinding
import com.dev.ahoyweatherapp.utils.extensions.hide
import com.dev.ahoyweatherapp.utils.extensions.show
import com.mikhaellopez.rxanimation.*
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
/**
 * Created by Bino on 2022-05-30
 */
@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(
    R.layout.fragment_splash,
    SplashFragmentViewModel::class.java,
) {
    var disposable = CompositeDisposable()

    override fun init() {
        super.init()

        if (binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "")
                .isNullOrEmpty()
        ) {
            binding.buttonExplore.hide()
            binding.viewModel?.navigateDashboard = true
        } else {
            binding.buttonExplore.hide()
            binding.viewModel?.navigateDashboard = true
        }

        binding.viewModel?.navigateDashboard?.let { startSplashAnimation(it) }

        binding.buttonExplore.setOnClickListener {
            binding.viewModel?.navigateDashboard?.let { it1 -> endSplashAnimation(it1) }
        }

        binding.rootView.setOnClickListener {
            binding.viewModel?.navigateDashboard?.let { it1 -> endSplashAnimation(it1) }
        }
    }

    private fun startSplashAnimation(navigateToDashboard: Boolean) {

        Handler().postDelayed(Runnable {
            findNavController().graph.setStartDestination(R.id.dashboardFragment) // Little bit tricky solution :)
            if (navigateToDashboard) {
                endSplashAnimation(navigateToDashboard)
            }
        }, 3 * 1000)

    }

    private fun endSplashAnimation(navigateToDashboard: Boolean) {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(

                ),

                RxAnimation.together(

                ),

                RxAnimation.together(

                ),



            )
                .doOnTerminate {
                    findNavController().graph.setStartDestination(R.id.dashboardFragment) // Little bit tricky solution :)
                    if (navigateToDashboard) {
                        navigate(R.id.action_splashFragment_to_dashboardFragment)
                    } else {
                       // navigate(R.id.action_splashFragment_to_searchFragment)
                        navigate(R.id.action_splashFragment_to_dashboardFragment)
                    }
                }
                .subscribe()

        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
