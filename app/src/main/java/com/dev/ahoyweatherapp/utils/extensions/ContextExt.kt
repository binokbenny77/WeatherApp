package com.dev.ahoyweatherapp.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
/**
 * Created by Bino on 2022-05-30
 */
fun Context.getColorCompat(@ColorRes resourceId: Int) = ContextCompat.getColor(this, resourceId)

fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo: NetworkInfo?
    activeNetworkInfo = cm.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}
