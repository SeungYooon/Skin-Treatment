package com.example.toyproject.di

import androidx.fragment.app.Fragment
import com.example.toyproject.ui.myskin.MySkinAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object MySkinModule {
    @Provides
    fun provideMySkinAdapter(fragment: Fragment): MySkinAdapter =
        MySkinAdapter(fragment as? MySkinAdapter.OnClickListener)
}