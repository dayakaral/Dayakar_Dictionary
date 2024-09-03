package com.example.data.db.entities

// data/entities/WordEntity.kt

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.db.converter.ListDefinitionConverter
import com.example.data.db.converter.ListMeaningConverter
import com.example.data.db.converter.ListPhoneticConverter
import com.example.data.db.converter.ListStringConverter

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    @TypeConverters(ListStringConverter::class)
    val sourceUrls: List<String>,
    @Embedded(prefix = "license_") val license: LicenseEntity?,
    @TypeConverters(ListPhoneticConverter::class)
    val phonetics: List<PhoneticEntity>,
    @TypeConverters(ListMeaningConverter::class)
    val meanings: List<MeaningEntity>
)

data class PhoneticEntity(
    val text: String?,
    val audio: String?,
    val sourceUrl: String?,
    @Embedded(prefix = "license_") val license: LicenseEntity?
)

data class MeaningEntity(
    val partOfSpeech: String,
    @TypeConverters(ListDefinitionConverter::class)
    val definitions: List<DefinitionEntity>,
    @TypeConverters(ListStringConverter::class)
    val synonyms: List<String>,
    @TypeConverters(ListStringConverter::class)
    val antonyms: List<String>
)

data class DefinitionEntity(
    val definition: String,
    @TypeConverters(ListStringConverter::class)
    val synonyms: List<String>,
    @TypeConverters(ListStringConverter::class)
    val antonyms: List<String>,
    val example: String? = null
)

data class LicenseEntity(
    val name: String,
    val url: String
)
