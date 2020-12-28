package com.example.toyproject.network.api

import com.example.toyproject.data.Categories
import com.example.toyproject.data.Category
import io.reactivex.Single

interface ChannelHelper {
    fun getCategory(limit: Int): Single<Categories>
}