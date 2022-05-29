package com.dev.ahoyweatherapp.ui.dashboard

import com.dev.ahoyweatherapp.core.BaseViewState
import com.dev.ahoyweatherapp.db.entity.ForecastEntity
import com.dev.ahoyweatherapp.utils.domain.Status

/**
 * Created by Bino on 2022-05-30
 */

class ForecastViewState(
    val status: Status,
    val error: String? = null,
    val data: ForecastEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
