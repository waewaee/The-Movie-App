package com.waewaee.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.themovieapp.data.vos.GenreVO

class GenreIdsTypeConverter {
    @TypeConverter
    fun toString(genreList: List<Int>?) : String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreIds(genreListJsonString: String) : List<Int>? {
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreListJsonString, genreIdsListType)
    }
}