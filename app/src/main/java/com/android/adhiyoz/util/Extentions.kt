package com.android.adhiyoz.util

import android.content.Context
import android.content.res.Resources

fun Int.convertPixelToDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.convertDpToPixel(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Context.isAppInstalled(packageName: String): Boolean {
    return try {
        packageManager.getApplicationInfo(packageName, 0)
        true
    } catch (e: Exception) {
        false
    }
}