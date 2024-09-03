package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.api.DictionaryApiService
import com.example.data.db.DictionaryDatabase
import com.example.data.db.dao.DictionaryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DictionaryDataModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    // provides room db instance
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DictionaryDatabase {
        return Room.databaseBuilder(
            appContext,
            DictionaryDatabase::class.java,
            "dic_database"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: DictionaryDatabase): DictionaryDao {
        return appDatabase.dictionaryDao()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): DictionaryApiService {
        return retrofit.create(DictionaryApiService::class.java)
    }

}