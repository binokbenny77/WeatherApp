package com.dev.ahoyweatherapp.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.dev.ahoyweatherapp.domain.model.Clouds
import kotlinx.parcelize.Parcelize

/**
 * Created by Bino on 2022-05-30
 */

@Parcelize
@Entity(tableName = "Clouds")
data class CloudsEntity(
    @ColumnInfo(name = "all")
    var all: Int
) : Parcelable {
    @Ignore
    constructor(clouds: Clouds?) : this(
        all = clouds?.all ?: 0
    )
}
