package com.example.toyproject.data.remote.service

import com.example.toyproject.data.db.entities.SkinInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface SkinService {

    @GET("skins")
    suspend fun loadAllSkins(): List<SkinInfo>

    @GET("skins/skin_title/{skin_title}")
    suspend fun loadSkinsByTitle(@Path("skin_title") skinTitle: String): List<SkinInfo>
}