package com.example.toyproject.di

import com.example.toyproject.data.remote.SkinDataSource
import com.example.toyproject.data.remote.SkinDataSourceImpl
import com.example.toyproject.data.remote.service.SkinService
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
    fun provideSkinService(retrofit: Retrofit): SkinService =
        retrofit.create(SkinService::class.java)

    @Provides
    @Singleton
    fun provideSkinDataSource(skinDataSourceImpl: SkinDataSourceImpl): SkinDataSource =
        skinDataSourceImpl
}