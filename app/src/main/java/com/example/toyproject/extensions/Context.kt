package com.example.toyproject.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.StringRes


fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Context.showToast(@StringRes textId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, textId, duration).show()

fun Context.toPixels(unit: Int = TypedValue.COMPLEX_UNIT_DIP, value: Number): Int =
    TypedValue.applyDimension(unit, value.toFloat(), resources.displayMetrics).toInt()
