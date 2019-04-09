package com.rofhiwa.weatherapp.extensions

import android.content.Context
import android.widget.Toast

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.getDimen(dimenId: Int): Int {
    return resources.getDimension(dimenId).toInt()
}