package com.example.toyproject.util.extensions

import android.content.Context
import android.util.TypedValue

fun Context.toPixels(unit: Int = TypedValue.COMPLEX_UNIT_DIP, value: Number): Int =
    TypedValue.applyDimension(unit, value.toFloat(), resources.displayMetrics).toInt()
