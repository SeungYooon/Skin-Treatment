package com.example.toyproject.data.remote.service

import com.example.toyproject.data.db.entities.Skins
import retrofit2.http.GET
import retrofit2.http.Path

interface SkinService {

    @GET("skins")
    suspend fun getAllSkins(): List<Skins>

    @GET("skins/skin_title/{skin_title}")
    suspend fun getSkinsByTitle(@Path("skin_title") skinTitle: String): List<Skins>
}