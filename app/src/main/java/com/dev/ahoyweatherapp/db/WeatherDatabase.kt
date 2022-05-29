package com.dev.ahoyweatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dev.ahoyweatherapp.db.dao.CitiesForSearchDao
import com.dev.ahoyweatherapp.db.dao.CurrentWeatherDao
import com.dev.ahoyweatherapp.db.dao.FavoriteDao
import com.dev.ahoyweatherapp.db.dao.ForecastDao
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.db.entity.CurrentWeatherEntity
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.db.entity.ForecastEntity
import com.dev.ahoyweatherapp.utils.typeconverters.DataConverter

/**
 * Created by Bino on 2022-05-30
 */
@Database(
    entities = [
        ForecastEntity::class,
        CurrentWeatherEntity::class,
        CitiesForSearchEntity::class,FavoriteEntity::class
    ],
    version = 2
)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun citiesForSearchDao(): CitiesForSearchDao

    abstract fun favoriteDao(): FavoriteDao
}
