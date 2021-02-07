package com.example.toyproject.domain.repository

import com.example.toyproject.data.db.entities.Skins
import com.example.toyproject.util.Resource
import kotlinx.coroutines.flow.Flow

interface SkinRepository {
    fun getAllSkins(): Flow<Resource<List<Skins>>>

    fun getSkinsByTitle(skinTitle: String): Flow<List<Skins>>

    fun loadAllSkins(): Flow<List<Skins>>

    fun loadBySkinTitle(skinTitle: String): Flow<List<Skins>>

    fun searchBySkinKinds(skinKinds: String): Flow<List<Skins>>

    suspend fun insertSkins(skins: Skins)

    suspend fun deleteAllSkins()

    suspend fun deleteOneSkin(skinKinds: String)
}