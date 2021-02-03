package com.example.toyproject.util.extensions

import androidx.recyclerview.widget.RecyclerView
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

fun setAdapter(recyclerView: RecyclerView, baseAdapter: RecyclerView.Adapter<*>) {
    recyclerView.adapter = baseAdapter
}

fun bindAdapterTransform(view: DiscreteScrollView, isTransform: Boolean) {
    if (isTransform) {
        view.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build()
        )
    }
}