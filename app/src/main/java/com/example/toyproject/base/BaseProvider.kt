package com.example.toyproject.base

import kotlinx.coroutines.CoroutineDispatcher

interface BaseProvider {
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
}