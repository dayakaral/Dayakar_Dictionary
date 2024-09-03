package com.example.data.db.converter

import androidx.room.TypeConverter
import com.example.data.db.entities.DefinitionEntity
import com.example.data.db.entities.MeaningEntity
import com.example.data.db.entities.PhoneticEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ListStringConverter {
    @TypeConverter
    fun fromList(strings: List<String>): String = Gson().toJson(strings)

    @TypeConverter
    fun toList(data: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(data, listType)
    }
}

object ListPhoneticConverter {
    @TypeConverter
    fun fromList(phonetics: List<PhoneticEntity>): String = Gson().toJson(phonetics)

    @TypeConverter
    fun toList(data: String): List<PhoneticEntity> {
        val listType = object : TypeToken<List<PhoneticEntity>>() {}.type
        return Gson().fromJson(data, listType)
    }
}

object ListMeaningConverter {
    @TypeConverter
    fun fromList(meanings: List<MeaningEntity>): String = Gson().toJson(meanings)

    @TypeConverter
    fun toList(data: String): List<MeaningEntity> {
        val listType = object : TypeToken<List<MeaningEntity>>() {}.type
        return Gson().fromJson(data, listType)
    }
}

object ListDefinitionConverter {
    @TypeConverter
    fun fromList(definitions: List<DefinitionEntity>): String = Gson().toJson(definitions)

    @TypeConverter
    fun toList(data: String): List<DefinitionEntity> {
        val listType = object : TypeToken<List<DefinitionEntity>>() {}.type
        return Gson().fromJson(data, listType)
    }
}