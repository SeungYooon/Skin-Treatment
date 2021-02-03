package com.example.toyproject.di

import android.content.Context
import com.example.toyproject.data.db.SkinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideSkinDatabase(@ApplicationContext context: Context): SkinDatabase =
        SkinDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideSkinDao(database: SkinDatabase) = database.skinDao()
}