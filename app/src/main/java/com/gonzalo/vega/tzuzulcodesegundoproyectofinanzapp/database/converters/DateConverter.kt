package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? {
        return value?.let {
            var calendar = Calendar.getInstance()
            var date = Date(it)
            date.setTime(it)
                calendar.setTime(date)
            return calendar }
    }

    @TypeConverter
    fun dateToTimestamp(date: Calendar?): Long? {
        return date?.timeInMillis
    }
}