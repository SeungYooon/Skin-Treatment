package com.example.toyproject.di

import com.example.toyproject.ui.detail.DetailAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DetailModule {
    @Provides
    fun provideDetailAdapter(): DetailAdapter = DetailAdapter()
}