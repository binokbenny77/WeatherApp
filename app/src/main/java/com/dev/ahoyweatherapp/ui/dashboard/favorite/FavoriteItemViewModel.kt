package com.dev.ahoyweatherapp.ui.dashboard.favorite

import androidx.databinding.ObservableField
import com.dev.ahoyweatherapp.core.BaseViewModel
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.domain.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

@HiltViewModel
class FavoriteItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<FavoriteEntity>()
}
