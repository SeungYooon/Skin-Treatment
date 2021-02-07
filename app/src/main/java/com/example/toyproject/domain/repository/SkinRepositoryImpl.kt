package com.example.toyproject.domain.repository

import com.example.toyproject.data.db.dao.SkinDao
import com.example.toyproject.data.db.entities.Skins
import com.example.toyproject.data.remote.SkinDataSource
import com.example.toyproject.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SkinRepositoryImpl @Inject constructor(
    private val skinDataSource: SkinDataSource,
    private val skinDao: SkinDao
) : SkinRepository {
    override fun getAllSkins(): Flow<Resource<List<Skins>>> = skinDataSource.getAllSkins().map {
        Resource.success(it)
    } // get all skins from api

    override fun getSkinsByTitle(skinTitle: String): Flow<List<Skins>> =
        skinDataSource.getSkinsByTitle(skinTitle) // get skins by title from api

    override fun loadAllSkins(): Flow<List<Skins>> =
        skinDao.loadAllSkins() // get all skins from local

    override fun loadBySkinTitle(skinTitle: String): Flow<List<Skins>> =
        skinDao.loadBySkinTitle(skinTitle) // get skins by title from local

    override fun searchBySkinKinds(skinKinds: String): Flow<List<Skins>> =
        skinDao.searchBySkinKinds(skinKinds) // search skins by kinds

    override suspend fun insertSkins(skins: Skins) = skinDao.insertSkins(skins) // insert skins

    override suspend fun deleteAllSkins() = skinDao.deleteAllSkins() // remove all skin

    override suspend fun deleteOneSkin(skinKinds: String) =
        skinDao.deleteOneSkin(skinKinds) // remove one skin
}