package com.dev.ahoyweatherapp.ui.search

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.dev.ahoyweatherapp.core.BaseViewModel
import com.dev.ahoyweatherapp.core.Constants
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.db.entity.CoordEntity
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.domain.usecase.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val useCase: SearchCitiesUseCase,
    private val pref: SharedPreferences
) : BaseViewModel() {

    init {


    }

//    var location: ObservableList<FavoriteEntity> = ObservableArrayList<FavoriteEntity>()
   // var location = LiveData<List<FavoriteEntity>>()
   // private var location = MutableLiveData<List<FavoriteEntity>>()
   // private var location = MutableLiveData<List<FavoriteEntity>>
    //rivate val favorites: MutableLiveData<List<FavoriteEntity>>? = emptyList()

    private val _searchParams: MutableLiveData<SearchCitiesUseCase.SearchCitiesParams> = MutableLiveData()
    private val uiScope = CoroutineScope(Dispatchers.Main)
    fun getSearchViewState() = searchViewState

    private val searchViewState: LiveData<SearchViewState> = _searchParams.switchMap { params ->
        useCase.execute(params)
    }

    fun setSearchParams(params: SearchCitiesUseCase.SearchCitiesParams) {
        if (_searchParams.value == params) {
            return
        }
        _searchParams.postValue(params)
    }

    fun saveCoordsToSharedPref(coordEntity: CoordEntity): Single<String>? {
        return Single.create<String> {
            pref.edit().putString(Constants.Coords.LAT, coordEntity.lat.toString()).apply()
            pref.edit().putString(Constants.Coords.LON, coordEntity.lon.toString()).apply()
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


    fun insertFavoriteList(item: CitiesForSearchEntity)
    {
            uiScope.launch {
                 insertFavorite(item)
        }

    }

    private suspend fun insertFavorite(item:CitiesForSearchEntity) = withContext(Dispatchers.Default) {
        useCase.getRepository().insertFavoriteList(item)

    }




}
