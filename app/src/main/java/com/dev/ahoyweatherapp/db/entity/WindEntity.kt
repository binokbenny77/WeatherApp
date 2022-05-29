package com.dev.ahoyweatherapp.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.dev.ahoyweatherapp.domain.model.Wind
import kotlinx.parcelize.Parcelize

/**
 * Created by Bino on 2022-05-30
 */

@Parcelize
@Entity(tableName = "Wind")
data class WindEntity(
    @ColumnInfo(name = "deg")
    val deg: Double?,
    @ColumnInfo(name = "speed")
    val speed: Double?
) : Parcelable {
    @Ignore
    constructor(wind: Wind?) : this(
        deg = wind?.deg,
        speed = wind?.speed
    )
}
