package com.dev.ahoyweatherapp.ui.main

import androidx.databinding.ObservableField
import com.dev.ahoyweatherapp.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
/**
 * Created by Bino on 2022-05-30
 */
@HiltViewModel
class MainActivityViewModel @Inject internal constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}
