package com.dev.ahoyweatherapp.domain.datasource.currentWeather

import com.dev.ahoyweatherapp.db.dao.CurrentWeatherDao
import com.dev.ahoyweatherapp.db.entity.CurrentWeatherEntity
import com.dev.ahoyweatherapp.domain.model.CurrentWeatherResponse
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class CurrentWeatherLocalDataSource @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao
) {

    fun getCurrentWeather() = currentWeatherDao.getCurrentWeather()

    fun insertCurrentWeather(currentWeather: CurrentWeatherResponse) = currentWeatherDao.deleteAndInsert(
        CurrentWeatherEntity(currentWeather)
    )
}
