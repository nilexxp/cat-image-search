package com.example.catimagesearch.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context : Application){

    @Provides
    fun providesContext() : Context = context

}