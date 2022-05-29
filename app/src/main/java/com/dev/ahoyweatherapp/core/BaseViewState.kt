package com.dev.ahoyweatherapp.core

import com.dev.ahoyweatherapp.utils.domain.Status

/**
 * Created by Bino on 2022-05-30
 */

open class BaseViewState(val baseStatus: Status, val baseError: String?) {
    fun isLoading() = baseStatus == Status.LOADING
    fun getErrorMessage() = baseError
    fun shouldShowErrorMessage() = baseError != null
}
