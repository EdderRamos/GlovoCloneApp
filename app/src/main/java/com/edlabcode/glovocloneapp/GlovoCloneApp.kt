package com.edlabcode.glovocloneapp

import android.app.Application
import com.edlabcode.glovocloneapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class GlovoCloneApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@GlovoCloneApp)
        }
    }
}