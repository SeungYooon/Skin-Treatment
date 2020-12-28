package com.example.toyproject.di

import android.app.Activity
import com.example.toyproject.ui.skin.SkinAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ActivityComponent::class)
object SkinModule {

    @Provides
    fun provideSkinAdapter(activity: Activity): SkinAdapter =
        SkinAdapter(activity as? SkinAdapter.OnClickListener)
}