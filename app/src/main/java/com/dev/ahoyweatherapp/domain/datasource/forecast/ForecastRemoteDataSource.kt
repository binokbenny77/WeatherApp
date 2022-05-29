package com.dev.ahoyweatherapp.domain.datasource.forecast

import com.dev.ahoyweatherapp.domain.WeatherAppAPI
import com.dev.ahoyweatherapp.domain.model.ForecastResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getForecastByGeoCords(lat: Double, lon: Double, units: String): Single<ForecastResponse> = api.getForecastByGeoCords(
        lat,
        lon,
        units
    )
}
