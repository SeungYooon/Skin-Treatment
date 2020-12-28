package com.example.toyproject.network.api

import com.example.toyproject.data.Categories
import com.example.toyproject.data.Category
import io.reactivex.Single
import javax.inject.Inject

class ChannelHelperImpl @Inject constructor(
    private val channelApi: ChannelApi
) : ChannelHelper {
    override fun getCategory(limit: Int): Single<Categories> =
        channelApi.getCategories(limit = limit)
}