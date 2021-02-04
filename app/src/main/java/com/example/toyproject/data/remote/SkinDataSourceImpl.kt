package com.example.toyproject.data.remote

import com.example.toyproject.data.remote.service.SkinService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SkinDataSourceImpl @Inject constructor(
    private val skinService: SkinService
) : SkinDataSource {
    override fun getAllSkins() = flow {
        emit(skinService.getAllSkins())
    }

    override fun getSkinsByTitle(skinTitle: String) =
        flow { emit(skinService.getSkinsByTitle(skinTitle)) }
}