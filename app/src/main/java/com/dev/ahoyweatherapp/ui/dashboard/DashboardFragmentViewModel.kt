package com.dev.ahoyweatherapp.ui.dashboard

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.dev.ahoyweatherapp.core.BaseViewModel
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.domain.usecase.CurrentWeatherUseCase
import com.dev.ahoyweatherapp.domain.usecase.ForecastUseCase
import com.dev.ahoyweatherapp.domain.usecase.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

@HiltViewModel
class DashboardFragmentViewModel @Inject internal constructor(
    private val forecastUseCase: ForecastUseCase,
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val useCase: SearchCitiesUseCase,
    var sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val _forecastParams: MutableLiveData<ForecastUseCase.ForecastParams> = MutableLiveData()
    private val _currentWeatherParams: MutableLiveData<CurrentWeatherUseCase.CurrentWeatherParams> =
        MutableLiveData()
    private val uiScope = CoroutineScope(Dispatchers.Main)
    fun getForecastViewState() = forecastViewState
    fun getCurrentWeatherViewState() = currentWeatherViewState

    var favoriteList: ArrayList<FavoriteEntity> = ArrayList<FavoriteEntity>()
    var addedEvent: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val forecastViewState: LiveData<ForecastViewState> =
        _forecastParams.switchMap { params ->
            forecastUseCase.execute(params)
        }
    private val currentWeatherViewState: LiveData<CurrentWeatherViewState> =
        _currentWeatherParams.switchMap { params ->
            currentWeatherUseCase.execute(params)
        }

    fun setForecastParams(params: ForecastUseCase.ForecastParams) {
        if (_forecastParams.value == params) {
            return
        }
        _forecastParams.postValue(params)
    }

    fun setCurrentWeatherParams(params: CurrentWeatherUseCase.CurrentWeatherParams) {
        if (_currentWeatherParams.value == params) {
            return
        }
        _currentWeatherParams.postValue(params)
    }

    fun fetchFavorite() {
        uiScope.launch {
            fetchFavoriteData()
        }
    }

    private suspend fun fetchFavoriteData() = withContext(Dispatchers.Default) {
        useCase.getRepository().getFavoriteList().let { list ->

            if (list.isNotEmpty()) {
                favoriteList.clear()
                favoriteList.addAll(list)
            }

        }
    }
}
