package com.example.toyproject.data.remote

import com.example.toyproject.data.db.entities.Skins
import kotlinx.coroutines.flow.Flow

interface SkinDataSource {
    fun getSkinsByTitle(skinTitle: String): Flow<List<Skins>>
    fun getAllSkins(): Flow<List<Skins>>
}