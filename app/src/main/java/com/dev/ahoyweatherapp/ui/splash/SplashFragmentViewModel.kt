package com.dev.ahoyweatherapp.ui.splash

import android.content.SharedPreferences
import com.dev.ahoyweatherapp.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    var sharedPreferences: SharedPreferences
) : BaseViewModel() {
    var navigateDashboard = false
}
