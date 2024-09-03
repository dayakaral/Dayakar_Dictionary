package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.converter.ListDefinitionConverter
import com.example.data.db.converter.ListMeaningConverter
import com.example.data.db.converter.ListPhoneticConverter
import com.example.data.db.converter.ListStringConverter
import com.example.data.db.dao.DictionaryDao
import com.example.data.db.entities.WordEntity


@Database(entities = [WordEntity::class], version = 1)
@TypeConverters(ListStringConverter::class, ListPhoneticConverter::class, ListMeaningConverter::class, ListDefinitionConverter::class)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao

}