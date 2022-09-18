package com.example.catimagesearch.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catimagesearch.data.entity.SavedQueryModel

@Database(entities = [SavedQueryModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedQueryDao() : SavedQueryDao
}