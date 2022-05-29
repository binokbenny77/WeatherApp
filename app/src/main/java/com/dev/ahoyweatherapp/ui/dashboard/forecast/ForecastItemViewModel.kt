package com.dev.ahoyweatherapp.ui.dashboard.forecast

import androidx.databinding.ObservableField
import com.dev.ahoyweatherapp.core.BaseViewModel
import com.dev.ahoyweatherapp.domain.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

@HiltViewModel
class ForecastItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
