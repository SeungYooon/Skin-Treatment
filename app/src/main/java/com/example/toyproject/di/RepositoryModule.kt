package com.example.toyproject.di

import com.example.toyproject.data.SkinRepositoryImpl
import com.example.toyproject.domain.repository.SkinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSkinRepository(skinRepositoryImpl: SkinRepositoryImpl): SkinRepository =
        skinRepositoryImpl
}