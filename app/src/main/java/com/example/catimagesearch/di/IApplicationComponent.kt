package com.example.catimagesearch.di

import com.example.catimagesearch.di.modules.AppModule
import com.example.catimagesearch.di.modules.ContextModule
import com.example.catimagesearch.di.modules.DatabaseModule
import com.example.catimagesearch.ui.search_screen.SearchScreen
import dagger.Component

@Component(modules = [
    ContextModule::class,
    AppModule::class,
    DatabaseModule::class])
interface IApplicationComponent {
    fun inject(searchScreen: SearchScreen?)
}