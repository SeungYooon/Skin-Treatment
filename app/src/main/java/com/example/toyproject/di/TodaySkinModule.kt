package com.example.toyproject.di

import androidx.fragment.app.Fragment
import com.example.toyproject.ui.today.TodaySkinAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object TodaySkinModule {

    @Provides
    fun provideTodaySkinAdapter(fragment: Fragment): TodaySkinAdapter =
        TodaySkinAdapter(fragment as? TodaySkinAdapter.OnClickListener)
}