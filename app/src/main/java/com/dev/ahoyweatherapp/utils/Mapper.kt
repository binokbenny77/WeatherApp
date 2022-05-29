package com.dev.ahoyweatherapp.utils
/**
 * Created by Bino on 2022-05-30
 */

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}
