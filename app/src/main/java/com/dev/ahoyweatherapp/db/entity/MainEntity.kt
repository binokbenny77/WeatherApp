package com.dev.ahoyweatherapp.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.dev.ahoyweatherapp.domain.model.Main
import kotlinx.parcelize.Parcelize

/**
 * Created by Bino on 2022-05-30
 */

@Parcelize
@Entity(tableName = "Main")
data class MainEntity(
    @ColumnInfo(name = "temp")
    var temp: Double?,
    @ColumnInfo(name = "tempMin")
    var tempMin: Double?,
    @ColumnInfo(name = "grndLevel")
    var grndLevel: Double?,
    @ColumnInfo(name = "tempKf")
    var tempKf: Double?,
    @ColumnInfo(name = "humidity")
    var humidity: Int?,
    @ColumnInfo(name = "pressure")
    var pressure: Double?,
    @ColumnInfo(name = "seaLevel")
    var seaLevel: Double?,
    @ColumnInfo(name = "tempMax")
    var tempMax: Double?
) : Parcelable {
    @Ignore
    constructor(main: Main?) : this(
        temp = main?.temp,
        tempMin = main?.tempMin,
        grndLevel = main?.grndLevel,
        tempKf = main?.tempKf,
        humidity = main?.humidity,
        pressure = main?.pressure,
        seaLevel = main?.seaLevel,
        tempMax = main?.tempMax
    )

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }

    fun getHumidityString(): String {
        return humidity.toString() + "°"
    }
}
