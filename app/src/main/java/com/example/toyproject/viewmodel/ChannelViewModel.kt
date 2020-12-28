package com.example.toyproject.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.toyproject.base.BaseViewModel
import com.example.toyproject.data.Categories
import com.example.toyproject.data.Category
import com.example.toyproject.repository.ChannelRepository
import com.example.toyproject.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ChannelViewModel @ViewModelInject constructor(
    private val channelRepository: ChannelRepository
) : BaseViewModel() {

    private val categories = MutableLiveData<Resource<Categories>>()

    fun fetchCategory(limit: Int) {
        categories.postValue(Resource.loading(null))
        addDisposable(
            channelRepository.getCategory(limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    categories.postValue(Resource.success(it))
                }, {
                    categories.postValue(Resource.error(it.message, null))
                })
        )
    }

    fun getCategory(): LiveData<Resource<Categories>> = categories
}