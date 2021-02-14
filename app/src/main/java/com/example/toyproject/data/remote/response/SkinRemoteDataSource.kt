package com.example.toyproject.data.remote.response

import com.example.toyproject.data.remote.service.SkinService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SkinRemoteDataSource @Inject constructor(
    private val skinService: SkinService
) : SkinDataSource {
    override fun loadAllSkins() = flow { emit(skinService.loadAllSkins()) }

    override fun loadSkinsByTitle(skinTitle: String) =
        flow { emit(skinService.loadSkinsByTitle(skinTitle)) }
}