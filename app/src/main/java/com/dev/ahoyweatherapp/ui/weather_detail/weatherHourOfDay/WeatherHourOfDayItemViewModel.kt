package com.dev.ahoyweatherapp.ui.weather_detail.weatherHourOfDay

import androidx.databinding.ObservableField
import com.dev.ahoyweatherapp.core.BaseViewModel
import com.dev.ahoyweatherapp.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class WeatherHourOfDayItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
