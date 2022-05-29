package com.dev.ahoyweatherapp.di

import com.dev.ahoyweatherapp.domain.usecase.CurrentWeatherUseCase
import com.dev.ahoyweatherapp.domain.usecase.ForecastUseCase
import com.dev.ahoyweatherapp.domain.usecase.SearchCitiesUseCase
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
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository) =
        CurrentWeatherUseCase(currentWeatherRepository)

    @Provides
    @Singleton
    fun provideForecastUseCase(forecastRepository: ForecastRepository) =
        ForecastUseCase(forecastRepository)

    @Provides
    @Singleton
    fun provideSearchCitiesUseCase(searchCitiesRepository: SearchCitiesRepository) =
        SearchCitiesUseCase(searchCitiesRepository)

}