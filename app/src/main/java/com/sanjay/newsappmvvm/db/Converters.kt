package com.sanjay.newsappmvvm.db

import androidx.room.TypeConverter
import com.sanjay.newsappmvvm.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name,name)
    }

}