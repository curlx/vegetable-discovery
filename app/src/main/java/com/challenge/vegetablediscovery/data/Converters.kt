package com.challenge.vegetablediscovery.data

import androidx.room.TypeConverter
import com.challenge.vegetablediscovery.domain.model.Vitamin

class Converters {
    @TypeConverter
    fun vitaminToString(vitamin: Vitamin): String = vitamin.name

    @TypeConverter
    fun stringToVitamin(name: String): Vitamin = try { Vitamin.valueOf(name) } catch(ignored: Exception) { Vitamin.UNKNOWN }
}
