package com.example.toyproject.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    context?.showToast(text, duration)

fun Fragment.showToast(@StringRes textId: Int, duration: Int = Toast.LENGTH_SHORT) =
    context?.showToast(textId, duration)