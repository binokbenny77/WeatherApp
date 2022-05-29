package com.dev.ahoyweatherapp.domain.datasource.forecast

import com.dev.ahoyweatherapp.db.dao.ForecastDao
import com.dev.ahoyweatherapp.db.entity.ForecastEntity
import com.dev.ahoyweatherapp.domain.model.ForecastResponse
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class ForecastLocalDataSource @Inject constructor(private val forecastDao: ForecastDao) {

    fun getForecast() = forecastDao.getForecast()

    fun insertForecast(forecast: ForecastResponse) = forecastDao.deleteAndInsert(
        ForecastEntity(forecast)
    )
}
