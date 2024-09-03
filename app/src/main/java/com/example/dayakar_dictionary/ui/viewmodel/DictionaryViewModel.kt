package com.example.dayakar_dictionary.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetWordDetailsUseCase
import com.example.presentation.ui.viewmodel.WordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.domain.util.Result

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val getWordDetailsUseCase: GetWordDetailsUseCase
) : ViewModel() {

    val wordState = mutableStateOf<WordState>(WordState.Idle)
    //val wordState: State<WordState> = _wordState

    fun fetchWordDetails(word: String) {
        viewModelScope.launch {
            wordState.value = WordState.Loading
            val result = getWordDetailsUseCase(word)
            wordState.value = when (result) {
                is Result.Success -> WordState.Success(result.data)
                is Result.Error -> WordState.Error(result.message)
                is Result.Loading -> WordState.Loading
            }
        }
    }
}