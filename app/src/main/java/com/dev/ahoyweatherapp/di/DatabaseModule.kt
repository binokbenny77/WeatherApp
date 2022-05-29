package com.dev.ahoyweatherapp.di

import android.content.Context
import androidx.room.Room
import com.dev.ahoyweatherapp.db.WeatherDatabase
import com.dev.ahoyweatherapp.db.dao.CitiesForSearchDao
import com.dev.ahoyweatherapp.db.dao.CurrentWeatherDao
import com.dev.ahoyweatherapp.db.dao.FavoriteDao
import com.dev.ahoyweatherapp.db.dao.ForecastDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * Created by Bino on 2022-05-30
 */

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "WeatherApp-DB"
        ).build()

    @Provides
    @Singleton
    fun provideForecastDao(db: WeatherDatabase): ForecastDao = db.forecastDao()

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(db: WeatherDatabase): CurrentWeatherDao = db.currentWeatherDao()

    @Provides
    @Singleton
    fun provideCitiesForSearchDao(db: WeatherDatabase): CitiesForSearchDao = db.citiesForSearchDao()

    @Provides
    @Singleton
    fun provideFavoriteDao(db: WeatherDatabase): FavoriteDao = db.favoriteDao()
}