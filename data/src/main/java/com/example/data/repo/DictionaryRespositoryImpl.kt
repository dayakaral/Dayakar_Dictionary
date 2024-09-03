package com.example.data.repo

import com.example.data.api.DictionaryApiService
import com.example.data.db.dao.DictionaryDao
import com.example.data.mappers.toDomainModel
import com.example.data.mappers.toEntityModel
import com.example.domain.entities.Word
import com.example.domain.repo_abstract.DictionaryRepository
import com.example.domain.util.Result
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val apiService: DictionaryApiService,
    private val dictionaryDao: DictionaryDao
) : DictionaryRepository {

    override suspend fun getWordDetails(word: String): Result<Word> {
        return try {
            val localWord = dictionaryDao.getWord(word)
            if (localWord != null) {
                Result.Success(localWord.toDomainModel())
            } else {
                val response = apiService.getWordDetails(word)
                if (response.isSuccessful) {
                    val wordData = response.body()?.firstOrNull()
                    wordData?.let {
                        dictionaryDao.insertWord(it.toEntityModel())
                    }
                    if (wordData== null)
                        Result.Error("no word found")
                    else
                        Result.Success(wordData)
                } else {
                    Result.Error("Error fetching word details")
                }
            }
        } catch (e: Exception) {
            Result.Error(e.message?:"unknown error")
        }
    }
}
