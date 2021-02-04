package com.example.toyproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.toyproject.data.db.entities.Skins
import kotlinx.coroutines.flow.Flow

@Dao
interface SkinDao {
    @Query("SELECT * FROM skins")
    fun loadAllSkins(): Flow<List<Skins>> // call once

    @Query("SELECT * FROM skins WHERE skin_title=:skinTitle")
    fun loadBySkinTitle(skinTitle: String): Flow<List<Skins>>   // 시술 항목에 따라

    @Query("SELECT * FROM skins WHERE skin_kinds=:skinKinds")
    fun searchBySkinKinds(skinKinds: String): Flow<List<Skins>> // 시술명에 따라

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkins(skins: Skins)

    @Query("DELETE FROM skins")
    suspend fun deleteAllSkins()

    @Query("DELETE FROM skins  WHERE skin_kinds=:skinKinds")
    suspend fun deleteOneSkin(skinKinds: String)
}