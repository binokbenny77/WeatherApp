package com.dev.ahoyweatherapp.ui.search.result

import androidx.databinding.ObservableField
import com.dev.ahoyweatherapp.core.BaseViewModel
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

@HiltViewModel
class SearchResultItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<CitiesForSearchEntity>()
}
