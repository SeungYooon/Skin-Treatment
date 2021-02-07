package com.example.toyproject.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
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

class MySkinViewModel @ViewModelInject constructor(
    private val skinRepository: SkinRepository,
    private val dispatchers: BaseProvider,
    val skinTitle: String
) : ViewModel() {
    private val _skinLiveData = MutableLiveData<Resource<List<Skins>>>()
    val skinLiveData: LiveData<Resource<List<Skins>>>
        get() = _skinLiveData

    fun loadAllMySkin() = viewModelScope.launch {
        _skinLiveData.postValue(Resource.loading())
        skinRepository.loadAllSkins()
            .flowOn(dispatchers.default)
            .catch { e -> _skinLiveData.postValue(Resource.Error(e)) }
            .collect {
                _skinLiveData.postValue(Resource.success(it))
            }
    }

    fun fetchBySkinTitle(skinTitle: String) = viewModelScope.launch {
        _skinLiveData.postValue(Resource.loading())
        skinRepository.loadBySkinTitle(skinTitle)
            .flowOn(dispatchers.default)
            .catch { e -> _skinLiveData.postValue(Resource.error(e)) }
            .collect { _skinLiveData.postValue(Resource.Success(it)) }
    }

    fun deleteAllSkins() = viewModelScope.launch(dispatchers.default) {
        skinRepository.deleteAllSkins()
    }

    fun deleteOneSkin(skinKinds: String) = viewModelScope.launch(dispatchers.default) {
        skinRepository.deleteOneSkin(skinKinds)
    }

    @ExperimentalCoroutinesApi
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    @FlowPreview
    val searchResult = queryChannel
        .asFlow()
        .debounce(300)
        .distinctUntilChanged() // do not want to collect the same value
        .flatMapLatest { query ->
            if (query.isBlank()) {
                skinRepository.loadAllSkins()
            } else skinRepository.searchBySkinKinds(query)
        }
        .flowOn(dispatchers.default)
        .catch { e: Throwable ->
            e.printStackTrace()
        }
        .asLiveData()

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        queryChannel.close()
    }
}