package com.example.catimagesearch.di.modules

import android.content.Context
import androidx.room.Room
import com.example.catimagesearch.data.database.AppDatabase
import com.example.catimagesearch.data.database.SavedQueryDao
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class DatabaseModule {

    companion object{
        private val DB_NAME = "database"
    }

    @Provides
    fun providesSavedQueryDao(context : Context) : SavedQueryDao =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build().savedQueryDao()
}