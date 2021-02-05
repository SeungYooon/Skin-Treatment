package com.example.toyproject.di

import com.example.toyproject.base.BaseProvider
import com.example.toyproject.util.extensions.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DispatcherModule {

    @Provides
    fun provideDispatchers(): BaseProvider = DispatchersProvider
}