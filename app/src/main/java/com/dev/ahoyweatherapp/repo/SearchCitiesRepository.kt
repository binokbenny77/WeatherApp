package com.dev.ahoyweatherapp.repo

import androidx.lifecycle.LiveData
import com.dev.ahoyweatherapp.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.db.entity.CoordEntity
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.domain.datasource.favorite.FavoriteLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.dev.ahoyweatherapp.domain.model.SearchResponse
import com.dev.ahoyweatherapp.utils.domain.RateLimiter
import com.dev.ahoyweatherapp.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Bino on 2022-05-30
 */

class SearchCitiesRepository @Inject constructor(
    private val searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource,
    private val searchCitiesLocalDataSource: SearchCitiesLocalDataSource,
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
) {

    private val rateLimiter = RateLimiter<String>(1, TimeUnit.SECONDS)

    fun loadCitiesByCityName(cityName: String?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return object : NetworkBoundResource<List<CitiesForSearchEntity>, SearchResponse>() {
            override fun saveCallResult(item: SearchResponse) =
                searchCitiesLocalDataSource.insertCities(
                    item
                )

            override fun shouldFetch(data: List<CitiesForSearchEntity>?): Boolean {

                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<CitiesForSearchEntity>> =
                searchCitiesLocalDataSource.getCityByName(
                    cityName
                )

            override fun createCall(): Single<SearchResponse> =
                searchCitiesRemoteDataSource.getCityWithQuery(
                    cityName
                        ?: ""
                )

            override fun onFetchFailed() = rateLimiter.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }

    fun insertFavoriteList(favoriteList: CitiesForSearchEntity) {

        val favoriteItem: FavoriteEntity = FavoriteEntity(
            id = favoriteList.id, country = favoriteList.country,
            county = favoriteList.county, importance = favoriteList.importance,
            administrative = favoriteList.administrative,
            coord = favoriteList.coord, name = favoriteList.name, favorite = favoriteList.favorite
        )
        favoriteLocalDataSource.insertFavorite(favoriteItem)
    }

    fun getFavoriteList():List<FavoriteEntity> {

       return favoriteLocalDataSource.getFavoriteList()
    }
}
