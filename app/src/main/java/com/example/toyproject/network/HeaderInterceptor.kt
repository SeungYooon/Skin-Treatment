package com.example.toyproject.network

import com.example.toyproject.utils.Constants.ACCESS_KEY
import com.example.toyproject.utils.Constants.ACCESS_SECRET
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .header("Accept", "application/json")
                .header("x-access-key", ACCESS_KEY)
                .header("x-access-secret", ACCESS_SECRET)
                .build()
        )
    }
}