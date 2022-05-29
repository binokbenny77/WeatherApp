package com.dev.ahoyweatherapp.di

import com.dev.ahoyweatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.dev.ahoyweatherapp.domain.datasource.favorite.FavoriteLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import com.dev.ahoyweatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.dev.ahoyweatherapp.repo.CurrentWeatherRepository
import com.dev.ahoyweatherapp.repo.ForecastRepository
import com.dev.ahoyweatherapp.repo.SearchCitiesRepository
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
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(
        currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
        currentWeatherLocalDataSource: CurrentWeatherLocalDataSource,
    ) = CurrentWeatherRepository(currentWeatherRemoteDataSource, currentWeatherLocalDataSource)

    @Provides
    @Singleton
    fun provideForecastRepository(
        forecastRemoteDataSource: ForecastRemoteDataSource,
        forecastLocalDataSource: ForecastLocalDataSource,
    ) = ForecastRepository(forecastRemoteDataSource, forecastLocalDataSource)

    @Provides
    @Singleton
    fun provideSearchCitiesRepository(
        searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource,
        searchCitiesLocalDataSource: SearchCitiesLocalDataSource,favoriteLocalDataSource: FavoriteLocalDataSource
    ) = SearchCitiesRepository(searchCitiesRemoteDataSource, searchCitiesLocalDataSource,favoriteLocalDataSource)

}