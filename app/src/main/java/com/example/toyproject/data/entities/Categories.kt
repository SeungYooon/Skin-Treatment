package com.example.toyproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Categories(
    val categories: List<Category>
)

@Entity(tableName = "categories")
data class Category(
    @SerializedName("id")
    @PrimaryKey val id: String,
    @ColumnInfo(name = "channelId")
    @SerializedName("channelId")
    val channelId: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val categoryName: String,
    @ColumnInfo(name = "count")
    @SerializedName("count")
    val count: Int,
    @ColumnInfo(name = "createdAt")
    @SerializedName("createdAt")
    val createdAt: String
)

