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
import com.example.toyproject.util.Constants
import com.example.toyproject.util.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MySkinViewModel @ViewModelInject constructor(
    private val skinRepository: SkinRepository,
    private val dispatchers: BaseProvider,
    val skinTitle: String
) : ViewModel() {
    private val _uiState = MutableStateFlow<State<List<SkinInfo>>>(State.empty())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(dispatchers.io) {
            skinRepository.getAllSkins()
                .onStart {
                    _uiState.value = State.loading()
                    delay(300)
                }
                .catch { e -> _uiState.value = State.error(e) }
                .collect { skinList ->
                    if (skinList.isEmpty()) {
                        _uiState.value = State.empty()
                    } else _uiState.value = State.success(skinList)
                }
        }
    }

    fun deleteAllSkins() = viewModelScope.launch(dispatchers.io) {
        skinRepository.deleteAllSkins()
    }

    fun deleteOneSkin(skinKinds: String) = viewModelScope.launch(dispatchers.io) {
        skinRepository.deleteOneSkin(skinKinds)
    }

    @ExperimentalCoroutinesApi
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    @FlowPreview
    val searchResult = queryChannel
        .asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) {
                skinRepository.getAllSkins()
            } else skinRepository.searchBySkinKinds(query)
        }
        .flowOn(dispatchers.io)
        .catch { e -> Log.e(TAG, e.message.toString()) }
        .asLiveData()

    @ExperimentalCoroutinesApi
    private val fetchChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    @FlowPreview
    val fetchBySkinTitle = fetchChannel
        .asFlow()
        .debounce(100)
        .distinctUntilChanged()
        .flatMapLatest { skinTitle ->
            if (skinTitle.isBlank()) skinRepository.getAllSkins()
            else skinRepository.getSkinByTitle(skinTitle)
        }
        .flowOn(dispatchers.io)
        .catch { e -> Log.e(TAG, e.message.toString()) }
        .asLiveData()

    @ExperimentalCoroutinesApi
    fun fetchBySkinTitle(checkId: Int) {
        when (checkId) {
            R.id.chipToxin -> fetchChannel.offer(Constants.TOXIN)
            R.id.chipFiller -> fetchChannel.offer(Constants.FILLER)
            R.id.chipLifting -> fetchChannel.offer(Constants.LIFTING)
            R.id.chipInjection -> fetchChannel.offer(Constants.INJECT)
            R.id.chipWaxing -> fetchChannel.offer(Constants.WAXING)
            R.id.chipAcne -> fetchChannel.offer(Constants.ACNE)
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        queryChannel.close()
        fetchChannel.close()
        super.onCleared()
    }

    companion object {
        const val TAG = "MySkinViewModel"
    }
}