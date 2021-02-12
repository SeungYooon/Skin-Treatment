package com.example.toyproject.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.toyproject.R
import com.example.toyproject.base.BaseProvider
import com.example.toyproject.data.db.entities.SkinInfo
import com.example.toyproject.domain.repository.SkinRepository
import com.example.toyproject.util.Constants.ACNE
import com.example.toyproject.util.Constants.FILLER
import com.example.toyproject.util.Constants.INJECT
import com.example.toyproject.util.Constants.LIFTING
import com.example.toyproject.util.Constants.TOXIN
import com.example.toyproject.util.Constants.WAXING
import com.example.toyproject.util.State
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
    private val _isError = MutableStateFlow(State.error(null))
    private val _isLoading = MutableStateFlow(State.loading())

    val isError: StateFlow<State.Error> get() = _isError
    val isLoading: StateFlow<State.Loading> get() = _isLoading

    val allSkins = skinRepository.loadAllSkins()
        .onStart { _isLoading.value = State.loading() }
        .catch { e -> _isError.value = State.error(e) }
        .asLiveData(viewModelScope.coroutineContext + dispatchers.io)

    @ExperimentalCoroutinesApi
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    fun setSkinType(type: Int?) {
        when (type) {
            R.string.toxin -> queryChannel.offer(TOXIN)
            R.string.filler -> queryChannel.offer(FILLER)
            R.string.injection -> queryChannel.offer(INJECT)
            R.string.lifting -> queryChannel.offer(LIFTING)
            R.string.acne -> queryChannel.offer(ACNE)
            R.string.waxing -> queryChannel.offer(WAXING)
        }
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    val getSkinsByTitle = queryChannel
        .asFlow()
        .debounce(100)
        .flatMapLatest { skinTitle -> skinRepository.loadSkinsByTitle(skinTitle) }
        .flowOn(dispatchers.io)
        .catch { e -> Log.e(TAG, e.message.toString()) }
        .asLiveData(viewModelScope.coroutineContext)

    fun saveSkins(skinInfo: SkinInfo) = viewModelScope.launch(dispatchers.io) {
        skinRepository.insertSkins(skinInfo)
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        queryChannel.close()
        super.onCleared()
    }

    companion object {
        const val TAG = "SkinViewModel"
    }
}