package com.dev.ahoyweatherapp.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.dev.ahoyweatherapp.domain.model.Coord
import com.dev.ahoyweatherapp.domain.model.Geoloc
import kotlinx.parcelize.Parcelize

/**
 * Created by Bino on 2022-05-30
 */

@Parcelize
@Entity(tableName = "Coord")
data class CoordEntity(
    @ColumnInfo(name = "lon")
    val lon: Double?,
    @ColumnInfo(name = "lat")
    val lat: Double?
) : Parcelable {
    @Ignore
    constructor(coord: Coord) : this(
        lon = coord.lon,
        lat = coord.lat
    )

    @Ignore
    constructor(geoloc: Geoloc?) : this(
        lon = geoloc?.lng,
        lat = geoloc?.lat
    )
}
