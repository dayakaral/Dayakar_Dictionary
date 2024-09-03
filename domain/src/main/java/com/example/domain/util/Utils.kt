package com.example.domain.util

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String, val errorCode: Int? = null) : Result<Nothing>()
}
