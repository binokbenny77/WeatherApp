package com.dev.ahoyweatherapp.db.entity

import android.os.Parcelable
import android.text.SpannableString
import androidx.room.*
import com.dev.ahoyweatherapp.domain.model.HitsItem
import com.dev.ahoyweatherapp.utils.extensions.bold
import com.dev.ahoyweatherapp.utils.extensions.italic
import com.dev.ahoyweatherapp.utils.extensions.plus
import com.dev.ahoyweatherapp.utils.extensions.spannable
import kotlinx.parcelize.Parcelize
/**
 * Created by Bino on 2022-05-30
 */

@Parcelize
@Entity(tableName = "Favorite")
data class FavoriteEntity(
    @ColumnInfo(name = "administrative")
    val administrative: String?,
    @ColumnInfo(name = "Country")
    val country: String?,
    @Embedded
    val coord: CoordEntity?,
    @ColumnInfo(name = "fullName")
    val name: String?,
    @ColumnInfo(name = "county")
    val county: String?,
    @ColumnInfo(name = "favorite")
    var favorite: String?,
    @ColumnInfo(name = "importance")
    val importance: Int?,
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String
) : Parcelable {
    @Ignore
    constructor(hitsItem: HitsItem?) : this(
        country = hitsItem?.country,
        importance = hitsItem?.importance,
        administrative = hitsItem?.administrative?.first(),
        coord = CoordEntity(hitsItem?.geoloc),
        name = hitsItem?.localeNames?.first(),
        county = hitsItem?.county?.first(),
        favorite = hitsItem?.favorite,
        id = hitsItem?.objectID.toString()
    )

    fun getFullName(): SpannableString {
        return spannable {
            bold(name ?: "").plus(", ") +
                bold(county ?: "").plus(", ") +
                italic(administrative ?: "").plus(", ") +
                italic(country ?: "")
        }
    }
}
