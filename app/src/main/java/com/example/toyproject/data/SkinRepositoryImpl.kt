package com.example.toyproject.data

import com.example.toyproject.data.db.dao.SkinDao
import com.example.toyproject.data.db.entities.SkinInfo
import com.example.toyproject.data.remote.response.SkinDataSource
import com.example.toyproject.domain.repository.SkinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SkinRepositoryImpl @Inject constructor(
    private val skinDataSource: SkinDataSource,
    private val skinDao: SkinDao
) : SkinRepository {
    override fun loadAllSkins(): Flow<List<SkinInfo>> = skinDataSource.loadAllSkins()

    override fun loadSkinsByTitle(skinTitle: String): Flow<List<SkinInfo>> =
        skinDataSource.loadSkinsByTitle(skinTitle)

    override fun getAllSkins(): Flow<List<SkinInfo>> =
        skinDao.getAllSkins()

    override fun getSkinByTitle(skinTitle: String): Flow<List<SkinInfo>> =
        skinDao.getSkinByTitle(skinTitle)

    override fun searchBySkinKinds(skinKinds: String): Flow<List<SkinInfo>> =
        skinDao.searchBySkinKinds(skinKinds)

    override suspend fun insertSkins(skinInfo: SkinInfo) = skinDao.insertSkins(skinInfo)

    override suspend fun deleteAllSkins() = skinDao.deleteAllSkins()

    override suspend fun deleteOneSkin(skinKinds: String) =
        skinDao.deleteOneSkin(skinKinds)
}