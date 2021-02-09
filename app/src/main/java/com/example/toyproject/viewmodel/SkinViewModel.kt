package com.example.toyproject.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.toyproject.base.BaseProvider
import com.example.toyproject.data.db.entities.Skins
import com.example.toyproject.domain.repository.SkinRepository
import com.example.toyproject.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SkinViewModel @ViewModelInject constructor(
    private val skinRepository: SkinRepository,
    private val dispatchers: BaseProvider
) : ViewModel() {
    val getAllSkins = skinRepository.getAllSkins()
        .onStart { emit(Resource.loading()) }
        .catch { e -> emit(Resource.error(e)) }
        .asLiveData(viewModelScope.coroutineContext + dispatchers.io)

    @ExperimentalCoroutinesApi
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    @FlowPreview
    val getSkinsByTitle = queryChannel
        .asFlow()
        .debounce(100)
        .flatMapLatest { skinTitle -> skinRepository.getSkinsByTitle(skinTitle) }
        .flowOn(dispatchers.io)
        .catch { e: Throwable -> e.printStackTrace() }
        .asLiveData(viewModelScope.coroutineContext)

    fun saveSkins(skins: Skins) = viewModelScope.launch(dispatchers.default) {
        skinRepository.insertSkins(skins)
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        queryChannel.close()
        super.onCleared()
    }
}
