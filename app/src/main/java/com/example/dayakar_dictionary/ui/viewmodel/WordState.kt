package com.example.presentation.ui.viewmodel

import com.example.domain.entities.Word

sealed class WordState {
    data object Idle : WordState()
    data object Loading : WordState()
    data class Success(val word: Word) : WordState()
    data class Error(val message: String) : WordState()
}
