package com.example.toyproject.util

import java.io.IOException

sealed class Resource<T>(val data: T? = null, val message: String? = null)

class Loading<T>(data: T?) : Resource<T>(data)
class Success<T>(data: T) : Resource<T>(data)
class Fail<T>(data: T?, message: String) : Resource<T>(data, message)

fun <T> loading(data: T? = null): Resource<T> = Loading(data)
fun <T> success(data: T): Resource<T> = Success(data)
fun <T> fail(message: String, data: T? = null): Resource<T> = Fail(data, message)

/*
sealed class Resource<out T> {

    data class Success<T>(val data: T) : Resource<T>()

    data class Error(val exception: Throwable) : Resource<Nothing>() {

        val isNetworkError: Boolean get() = exception is IOException
    }

    object Empty : Resource<Nothing>()

    object Loading : Resource<Nothing>()

    companion object {

        */
/**
         * Return [Success] with [data] to emit.
         *//*

        fun <T> success(data: T) = Success(data)

        */
/**
         * Return [Error] with [exception] to emit.
         *//*

        fun error(exception: Throwable) = Error(exception)

        */
/**
         * Return [Empty].
         *//*

        fun empty() = Empty

        */
/**
         * Return [Loading].
         *//*

        fun loading() = Loading

        */
/**
         * Return [Empty] if [list] is empty, otherwise return [Success].
         *//*

        fun <T> successOrEmpty(list: List<T>): Resource<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}*/
