package com.example.toyproject.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "skins")
@Parcelize
data class SkinInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "skin_title")
    @SerializedName("skin_title")
    val skinTitle: String = "",
    @ColumnInfo(name = "skin_kinds")
    @SerializedName("skin_kinds")
    val skinKinds: String = "",
    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")
    val imageUrl: String = "",
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String = "",
    @ColumnInfo(name = "point")
    @SerializedName("point")
    val point: String = ""
) : Parcelable