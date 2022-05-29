package com.dev.ahoyweatherapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dev.ahoyweatherapp.core.Constants
import com.dev.ahoyweatherapp.db.entity.CurrentWeatherEntity
import com.dev.ahoyweatherapp.repo.CurrentWeatherRepository
import com.dev.ahoyweatherapp.ui.dashboard.CurrentWeatherViewState
import com.dev.ahoyweatherapp.utils.UseCaseLiveData
import com.dev.ahoyweatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class CurrentWeatherUseCase @Inject internal constructor(
    private val repository: CurrentWeatherRepository
) : UseCaseLiveData<CurrentWeatherViewState, CurrentWeatherUseCase.CurrentWeatherParams, CurrentWeatherRepository>() {

    override fun getRepository(): CurrentWeatherRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: CurrentWeatherParams?): LiveData<CurrentWeatherViewState> {
        return repository.loadCurrentWeatherByGeoCords(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
            units = params?.units ?: Constants.Coords.METRIC
        ).map {
            onCurrentWeatherResultReady(it)
        }
    }

    private fun onCurrentWeatherResultReady(resource: Resource<CurrentWeatherEntity>): CurrentWeatherViewState {
        return CurrentWeatherViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class CurrentWeatherParams(
        val lat: String = "",
        val lon: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
