package com.evangelidis.t_tnews

import android.app.Application
import com.evangelidis.t_tnews.databases.appModule
import org.koin.android.ext.android.startKoin

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}
