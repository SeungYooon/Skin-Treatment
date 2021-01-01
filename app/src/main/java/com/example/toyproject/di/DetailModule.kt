package com.example.toyproject.di

import androidx.fragment.app.Fragment
import com.example.toyproject.ui.detail.DetailAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DetailModule {

    @Provides
    fun provideDetailAdapter(fragment: Fragment): DetailAdapter =
        DetailAdapter(fragment as? DetailAdapter.OnClickListener)
}