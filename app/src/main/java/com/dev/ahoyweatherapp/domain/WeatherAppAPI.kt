package com.dev.ahoyweatherapp.domain

import com.dev.ahoyweatherapp.domain.model.CurrentWeatherResponse
import com.dev.ahoyweatherapp.domain.model.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Bino on 2022-05-30
 */

interface WeatherAppAPI {

    @GET("forecast")
    fun getForecastByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Single<ForecastResponse>

    @GET("weather")
    fun getCurrentByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Single<CurrentWeatherResponse>
}
