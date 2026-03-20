package com.example.airport.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.airport.data.model.Cities
import kotlinx.datetime.Instant

class EnumConverter {
    @TypeConverter
    fun toEnumCity(value: String?): Cities? {
        return value?.let { Cities.valueOf(it) }
    }

    @TypeConverter
    fun fromInstant(value: Cities?): String? {
        return value?.name
    }
}
