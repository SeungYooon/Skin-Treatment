package com.example.toyproject.data.remote.response

import com.example.toyproject.data.db.entities.SkinInfo
import kotlinx.coroutines.flow.Flow

interface SkinDataSource {
    fun loadSkinsByTitle(skinTitle: String): Flow<List<SkinInfo>>
    fun loadAllSkins(): Flow<List<SkinInfo>>
}