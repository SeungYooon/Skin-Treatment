package com.example.toyproject.di

import com.example.toyproject.data.remote.api.ChannelApi
import com.example.toyproject.data.remote.api.ChannelHelper
import com.example.toyproject.data.remote.api.ChannelHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideChannelApi(retrofit: Retrofit): ChannelApi = retrofit.create(ChannelApi::class.java)

    @Provides
    @Singleton
    fun provideChannelHelper(channelHelper: ChannelHelperImpl): ChannelHelper = channelHelper
}