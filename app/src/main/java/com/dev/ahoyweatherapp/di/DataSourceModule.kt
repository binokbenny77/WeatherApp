package com.dev.ahoyweatherapp.di

import com.algolia.search.saas.places.PlacesClient
import com.dev.ahoyweatherapp.db.dao.CitiesForSearchDao
import com.dev.ahoyweatherapp.db.dao.CurrentWeatherDao
import com.dev.ahoyweatherapp.db.dao.FavoriteDao
import com.dev.ahoyweatherapp.db.dao.ForecastDao
import com.dev.ahoyweatherapp.domain.WeatherAppAPI
import com.dev.ahoyweatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.dev.ahoyweatherapp.domain.datasource.favorite.FavoriteLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import com.dev.ahoyweatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * Created by Bino on 2022-05-30
 */

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRemoteDataSource(api: WeatherAppAPI) =
        CurrentWeatherRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideForecastRemoteDataSource(api: WeatherAppAPI) =
        ForecastRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideSearchCitiesRemoteDataSource(
        client: PlacesClient,
        moshi: Moshi,
    ) = SearchCitiesRemoteDataSource(client, moshi)

    @Provides
    @Singleton
    fun provideCurrentWeatherLocalDataSource(currentWeatherDao: CurrentWeatherDao) =
        CurrentWeatherLocalDataSource(currentWeatherDao)

    @Provides
    @Singleton
    fun provideForecastLocalDataSource(forecastDao: ForecastDao) =
        ForecastLocalDataSource(forecastDao)

    @Provides
    @Singleton
    fun provideSearchCitiesLocalDataSource(citiesForSearchDao: CitiesForSearchDao) =
        SearchCitiesLocalDataSource(citiesForSearchDao)

    @Provides
    @Singleton
    fun provideFavoriteDataSource(favoriteDao: FavoriteDao) =
        FavoriteLocalDataSource(favoriteDao)
}