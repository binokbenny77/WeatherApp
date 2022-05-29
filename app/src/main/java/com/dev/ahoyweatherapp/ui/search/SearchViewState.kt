package com.dev.ahoyweatherapp.ui.search

import com.dev.ahoyweatherapp.core.BaseViewState
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.utils.domain.Status

/**
 * Created by Bino on 2022-05-30
 */

class SearchViewState(
    val status: Status,
    val error: String? = null,
    val data: List<CitiesForSearchEntity>? = null
) : BaseViewState(status, error) {
    fun getSearchResult() = data
}
