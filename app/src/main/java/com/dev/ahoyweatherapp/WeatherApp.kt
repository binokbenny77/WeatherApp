package com.dev.ahoyweatherapp

import android.app.Application
import android.os.Build
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
/**
 * Created by Bino on 2022-05-30
 */
@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG && !isRoboUnitTest()) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        AndroidThreeTen.init(this)
    }

    private fun isRoboUnitTest(): Boolean {
        return "robolectric" == Build.FINGERPRINT
    }

}
