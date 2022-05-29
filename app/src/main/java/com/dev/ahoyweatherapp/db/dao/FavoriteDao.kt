package com.dev.ahoyweatherapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.db.entity.CurrentWeatherEntity
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity

/**
 * Created by Bino on 2022-05-30
 */

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM Favorite")
    fun getFavoriteList(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Transaction
    fun deleteAndInsert(favoriteEntity: FavoriteEntity) {
        deleteFavorite()
        insertFavorite(favoriteEntity)
    }

    @Query("DELETE FROM Favorite")
    fun deleteFavorite()

    @Query("Select count(*) from Favorite")
    fun getCount(): Int
}
