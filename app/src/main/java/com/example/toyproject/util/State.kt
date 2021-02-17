package com.example.toyproject.util

import com.example.toyproject.data.db.entities.SkinInfo

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error(val exception: Throwable?) : State<Nothing>()
    object Loading : State<Nothing>()
    object Empty : State<Nothing>()

    companion object {
        fun success(data: List<SkinInfo>) = Success(data)
        fun error(exception: Throwable?) = Error(exception)
        fun loading() = Loading
        fun empty() = Empty
    }
}

