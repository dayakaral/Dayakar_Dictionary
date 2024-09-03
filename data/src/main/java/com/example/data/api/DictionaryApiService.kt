package com.example.data.api

import com.example.domain.entities.Word
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {
    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordDetails(@Path("word") word: String): Response<List<Word>>
}
