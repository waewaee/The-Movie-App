package com.waewaee.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.themovieapp.data.vos.ProductionCountryVO

class ProductionCountryTypeConverter {
    @TypeConverter
    fun toString(productionCountryList: List<ProductionCountryVO>?) : String {
        return Gson().toJson(productionCountryList)
    }

    @TypeConverter
    fun toProductionCountries(productionCountryJsonString: String) : List<ProductionCountryVO>? {
        val productionCountriesListType = object : TypeToken<List<ProductionCountryVO>?>() {}.type
        return Gson().fromJson(productionCountryJsonString, productionCountriesListType)
    }
}