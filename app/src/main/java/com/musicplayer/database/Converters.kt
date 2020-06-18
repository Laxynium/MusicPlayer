package com.musicplayer.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromString(value: String): UUID {
        return UUID.fromString(value);
    }
    @TypeConverter
    fun fromUUID(id: UUID): String {
        return id.toString();
    }
}