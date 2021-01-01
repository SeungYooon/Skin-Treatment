package com.example.toyproject.data.remote.api

import com.example.toyproject.data.entities.Categories
import io.reactivex.Single

interface ChannelHelper {
    fun getCategory(limit: Int): Single<Categories>
}