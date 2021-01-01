package com.example.toyproject.data.remote.api

import com.example.toyproject.data.entities.Categories
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ChannelApi {

    @GET("user-chat/categories")
    fun getCategories(@Query("limit") limit: Int): Single<Categories>
}