package com.example.catimagesearch

import android.app.Application
import com.example.catimagesearch.di.DaggerIApplicationComponent
import com.example.catimagesearch.di.IApplicationComponent
import com.example.catimagesearch.di.modules.AppModule
import com.example.catimagesearch.di.modules.ContextModule

class App: Application() {

    companion object{
        lateinit var appComponent: IApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerIApplicationComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

}