package com.example.domain.repo_abstract

import com.example.domain.entities.Word
import com.example.domain.util.Result

interface DictionaryRepository {
    suspend fun getWordDetails(word: String): Result<Word>
}