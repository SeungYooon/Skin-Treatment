package com.example.toyproject

import android.app.Application
import com.zoyi.channel.plugin.android.ChannelIO
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ChannelIO.initialize(this)
    }
}