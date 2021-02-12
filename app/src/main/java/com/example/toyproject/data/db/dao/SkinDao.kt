package com.example.toyproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.toyproject.data.db.entities.SkinInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface SkinDao {
    @Query("SELECT * FROM skins")
    fun getAllSkins(): Flow<List<SkinInfo>>

    @Query("SELECT * FROM skins WHERE skin_title=:skinTitle")
    fun getSkinByTitle(skinTitle: String): Flow<List<SkinInfo>>

    @Query("SELECT * FROM skins WHERE skin_kinds=:skinKinds")
    fun searchBySkinKinds(skinKinds: String): Flow<List<SkinInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkins(skinInfo: SkinInfo)

    @Query("DELETE FROM skins")
    suspend fun deleteAllSkins()

    @Query("DELETE FROM skins  WHERE skin_kinds=:skinKinds")
    suspend fun deleteOneSkin(skinKinds: String)
}