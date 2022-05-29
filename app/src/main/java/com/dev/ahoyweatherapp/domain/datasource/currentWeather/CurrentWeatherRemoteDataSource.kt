package com.dev.ahoyweatherapp.domain.datasource.currentWeather

import com.dev.ahoyweatherapp.domain.WeatherAppAPI
import com.dev.ahoyweatherapp.domain.model.CurrentWeatherResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class CurrentWeatherRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getCurrentWeatherByGeoCords(lat: Double, lon: Double, units: String): Single<CurrentWeatherResponse> = api.getCurrentByGeoCords(
        lat,
        lon,
        units
    )
}
