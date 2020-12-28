package com.example.toyproject.data

import com.google.gson.annotations.SerializedName

data class Categories(
    val categories: List<Category>
)
data class Category(
    @SerializedName("id")
    val id: String,
    @SerializedName("channelId")
    val channelId: String,
    @SerializedName("name")
    val categoryName: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("createdAt")
    val createdAt: String
)

