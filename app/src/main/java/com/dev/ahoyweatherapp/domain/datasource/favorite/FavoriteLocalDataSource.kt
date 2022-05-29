package com.dev.ahoyweatherapp.domain.datasource.favorite

import androidx.lifecycle.LiveData
import com.dev.ahoyweatherapp.db.dao.FavoriteDao
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.domain.model.HitsItem
import javax.inject.Inject
import kotlin.concurrent.thread

/**
 * Created by Bino on 2022-05-30
 */

class FavoriteLocalDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) {

    fun getFavoriteList(): List<FavoriteEntity> {
       return favoriteDao.getFavoriteList()
    }

    fun insertFavorite(insertFavorite: FavoriteEntity) {
        val favoriteItem:HitsItem = HitsItem(
            country = insertFavorite.country,importance = insertFavorite.importance,
           favorite=insertFavorite.favorite)

            favoriteDao.insertFavorite(insertFavorite)


    }
}
