package com.dev.ahoyweatherapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.repo.SearchCitiesRepository
import com.dev.ahoyweatherapp.ui.search.SearchViewState
import com.dev.ahoyweatherapp.utils.UseCaseLiveData
import com.dev.ahoyweatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class SearchCitiesUseCase @Inject internal constructor(
    private val repository: SearchCitiesRepository
) : UseCaseLiveData<SearchViewState, SearchCitiesUseCase.SearchCitiesParams, SearchCitiesRepository>() {

    override fun getRepository(): SearchCitiesRepository = repository

    override fun buildUseCaseObservable(params: SearchCitiesParams?): LiveData<SearchViewState> {
        return repository.loadCitiesByCityName(
            cityName = params?.city ?: ""
        ).map {
            onSearchResultReady(it)
        }
    }

    private fun onSearchResultReady(resource: Resource<List<CitiesForSearchEntity>>): SearchViewState {
        return SearchViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class SearchCitiesParams(
        val city: String = ""
    ) : Params()
}
