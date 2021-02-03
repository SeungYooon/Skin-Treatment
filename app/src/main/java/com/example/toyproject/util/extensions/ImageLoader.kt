package com.example.toyproject.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun bindImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}