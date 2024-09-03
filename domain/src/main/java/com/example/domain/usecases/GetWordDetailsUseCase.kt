package com.example.domain.usecases

import com.example.domain.entities.Word
import com.example.domain.repo_abstract.DictionaryRepository
import com.example.domain.util.Result

class GetWordDetailsUseCase(private val repository: DictionaryRepository) {
    suspend operator fun invoke(word: String): Result<Word> {
        return repository.getWordDetails(word)
    }
}
