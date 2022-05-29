package com.dev.ahoyweatherapp.core

import androidx.annotation.IntDef

/**
 * Created by Bino on 2022-05-30
 */

object Constants {


    private const val SIT = 0

    private const val DEV = 1

    private const val UAT = 2

    private const val PROD = 3

    @DeploymentType
    private val defaultEnvironment = DEV

    // HOST Urls
    private const val URL_SIT = "http://api.openweathermap.org/data/2.5/" //Put the SIT url here
    private const val URL_DEV = "http://api.openweathermap.org/data/2.5/"
    private const val URL_PROD = "http://api.openweathermap.org/data/2.5/" // Put the production url here
    private const val URL_UAT = ""


    val BASE_URL: String
        get() {

            return when (defaultEnvironment) {

                SIT -> URL_SIT

                DEV -> URL_DEV

                UAT -> URL_UAT

                PROD -> URL_PROD

                else -> URL_DEV
            }
        }

    @IntDef(SIT, DEV, UAT, PROD)
    private annotation class DeploymentType


    object NetworkService {
        const val API_KEY_VALUE = "751d80f6c314139192ffcb62c107e654"
        const val RATE_LIMITER_TYPE = "data"
        const val API_KEY_QUERY = "appid"
    }

    object AlgoliaKeys {
        const val APPLICATION_ID = "plNW8IW0YOIN"
        const val SEARCH_API_KEY = "029766644cb160efa51f2a32284310eb"
    }

    object Coords {
        const val LAT = "lat"
        const val LON = "lon"
        const val METRIC = "metric"
    }
}
