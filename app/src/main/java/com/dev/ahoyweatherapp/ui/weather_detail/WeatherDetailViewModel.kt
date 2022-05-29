package com.dev.ahoyweatherapp.ui.weather_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.ahoyweatherapp.core.BaseViewModel
import com.dev.ahoyweatherapp.db.entity.ForecastEntity
import com.dev.ahoyweatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.dev.ahoyweatherapp.domain.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val forecastLocalDataSource: ForecastLocalDataSource
) : BaseViewModel() {

    var weatherItem: ObservableField<ListItem> = ObservableField()
    private var forecastLiveData: LiveData<ForecastEntity> = MutableLiveData()
    var selectedDayDate: String? = null
    var selectedDayForecastLiveData: MutableLiveData<List<ListItem>> = MutableLiveData()

    fun getForecastLiveData() = forecastLiveData

    fun getForecast(): LiveData<ForecastEntity> {
        return forecastLocalDataSource.getForecast()
    }
}
