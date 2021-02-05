package com.example.toyproject.di

import androidx.fragment.app.Fragment
import com.example.toyproject.ui.skin.SkinAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object SkinModule {

    @Provides
    fun provideSkinAdapter(fragment: Fragment): SkinAdapter =
        SkinAdapter(fragment as? SkinAdapter.OnClickListener)
}