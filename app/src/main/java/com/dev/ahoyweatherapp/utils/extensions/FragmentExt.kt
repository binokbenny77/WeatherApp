package com.dev.ahoyweatherapp.utils.extensions

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
/**
 * Created by Bino on 2022-05-30
 */
fun androidx.fragment.app.Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = activity?.toast(
    message,
    duration
)

inline fun androidx.fragment.app.Fragment.alertDialog(
    body: AlertDialog.Builder.() -> AlertDialog.Builder
) = activity?.alertDialog(body)
