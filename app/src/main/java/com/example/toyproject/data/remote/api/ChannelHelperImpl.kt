package com.example.toyproject.data.remote.api

import com.example.toyproject.data.entities.Categories
import io.reactivex.Single
import javax.inject.Inject

class ChannelHelperImpl @Inject constructor(
    private val channelApi: ChannelApi
) : ChannelHelper {
    override fun getCategory(limit: Int): Single<Categories> =
        channelApi.getCategories(limit = limit)
}