package com.dev.ahoyweatherapp.repo

import androidx.lifecycle.LiveData
import com.dev.ahoyweatherapp.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.dev.ahoyweatherapp.db.entity.CurrentWeatherEntity
import com.dev.ahoyweatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.dev.ahoyweatherapp.domain.model.CurrentWeatherResponse
import com.dev.ahoyweatherapp.utils.domain.RateLimiter
import com.dev.ahoyweatherapp.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class CurrentWeatherRepository @Inject constructor(
    private val currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
    private val currentWeatherLocalDataSource: CurrentWeatherLocalDataSource
) {

    private val currentWeatherRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadCurrentWeatherByGeoCords(
        lat: Double,
        lon: Double,
        fetchRequired: Boolean,
        units: String
    ): LiveData<Resource<CurrentWeatherEntity>> {
        return object : NetworkBoundResource<CurrentWeatherEntity, CurrentWeatherResponse>() {
            override fun saveCallResult(item: CurrentWeatherResponse) = currentWeatherLocalDataSource.insertCurrentWeather(
                item
            )

            override fun shouldFetch(data: CurrentWeatherEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<CurrentWeatherEntity> = currentWeatherLocalDataSource.getCurrentWeather()

            override fun createCall(): Single<CurrentWeatherResponse> = currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(
                lat,
                lon,
                units
            )

            override fun onFetchFailed() = currentWeatherRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
