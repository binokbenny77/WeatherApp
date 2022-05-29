package com.dev.ahoyweatherapp.domain.datasource.searchCities

import androidx.lifecycle.LiveData
import com.dev.ahoyweatherapp.db.dao.CitiesForSearchDao
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.domain.model.SearchResponse
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class SearchCitiesLocalDataSource @Inject constructor(
    private val citiesForSearchDao: CitiesForSearchDao
) {

    fun getCityByName(cityName: String?): LiveData<List<CitiesForSearchEntity>> = citiesForSearchDao.getCityByName(
        cityName
    )

    fun insertCities(response: SearchResponse) {
        response.hits
            ?.map { CitiesForSearchEntity(it) }
            ?.let { citiesForSearchDao.insertCities(it) }
    }
}
