package com.example.catimagesearch.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.catimagesearch.data.database.converters.QueryTypeConverter

@TypeConverters(QueryTypeConverter::class)
@Entity(tableName = "SavedQuery")
class SavedQueryModel(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    @field:TypeConverters(QueryTypeConverter::class)
    var query : String,
    var date :String
)