package com.example.catimagesearch.data.database.converters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class QueryTypeConverter {
    companion object {
        private const val DATE_FORMAT = "HH:mm dd.MM.yy"
    }
    @TypeConverter
    fun fromDate(date: String): Long? {
        return SimpleDateFormat(DATE_FORMAT).parse(date).time
    }

    @TypeConverter
    fun toStringDate(date: Long): String? {
        return SimpleDateFormat(DATE_FORMAT).format(Date(date))
    }

}