package com.example.toyproject.network.api

import com.example.toyproject.data.Categories
import com.example.toyproject.data.Category
import com.example.toyproject.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ChannelApi {

    @GET("user-chat/categories")
    fun getCategories(@Query("limit") limit: Int): Single<Categories>
}