package com.example.presentation.di

import com.example.data.repo.DictionaryRepositoryImpl
import com.example.domain.repo_abstract.DictionaryRepository
import com.example.domain.usecases.GetWordDetailsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [UsecaseModule.RepoAbstract::class])
@InstallIn(SingletonComponent::class)
class UsecaseModule {

    @Provides
    @Singleton
    fun provideDictionaryUseCase(dictionaryRepository: DictionaryRepository): GetWordDetailsUseCase {
        return GetWordDetailsUseCase(dictionaryRepository)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepoAbstract{

        @Binds
        @Singleton
        fun bindDictionaryRepository(dictionaryRepositoryImpl: DictionaryRepositoryImpl): DictionaryRepository

    }

}