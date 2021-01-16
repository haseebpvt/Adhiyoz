package com.android.adhiyoz.util

import android.content.res.Resources

fun Int.convertPixelToDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.convertDpToPixel(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}