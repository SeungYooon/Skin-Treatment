package com.example.toyproject.repository

import com.example.toyproject.data.Categories
import com.example.toyproject.data.Category
import com.example.toyproject.network.api.ChannelHelper
import io.reactivex.Single
import javax.inject.Inject

class ChannelRepository @Inject constructor(private val channelHelper: ChannelHelper) {
    fun getCategory(limit: Int): Single<Categories> = channelHelper.getCategory(limit)
}