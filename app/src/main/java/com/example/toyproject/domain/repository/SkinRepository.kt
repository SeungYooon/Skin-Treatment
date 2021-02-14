package com.example.toyproject.domain.repository

import com.example.toyproject.data.db.entities.SkinInfo
import kotlinx.coroutines.flow.Flow

interface SkinRepository {
    fun loadAllSkins(): Flow<List<SkinInfo>>

    fun loadSkinsByTitle(skinTitle: String): Flow<List<SkinInfo>>

    fun getAllSkins(): Flow<List<SkinInfo>>

    fun getSkinByTitle(skinTitle: String): Flow<List<SkinInfo>>

    fun searchBySkinKinds(skinKinds: String): Flow<List<SkinInfo>>

    suspend fun insertSkins(skinInfo: SkinInfo)

    suspend fun deleteAllSkins()

    suspend fun deleteOneSkin(skinKinds: String)
}