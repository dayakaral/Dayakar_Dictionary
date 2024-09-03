package com.example.dayakar_dictionary.ui.compose

import android.media.MediaPlayer
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dayakar_dictionary.ui.viewmodel.DictionaryViewModel
import com.example.domain.entities.Meaning
import com.example.domain.entities.Word
import com.example.presentation.ui.viewmodel.WordState
import java.net.URI

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier
) {

    val viewModel: DictionaryViewModel = viewModel()
    var wordState by remember {
        viewModel.wordState
    }

    when (wordState) {
        is WordState.Idle -> SearchScreen(viewModel::fetchWordDetails, modifier)
        is WordState.Loading -> LoadingScreen()
        is WordState.Success -> WordDetailsScreen((wordState as WordState.Success).word,{wordState = WordState.Idle}, modifier)
        is WordState.Error -> ErrorScreen((wordState as WordState.Error).message)
    }
}

@Composable
fun SearchScreen(onSearch: (String) -> Unit, modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Enter a word") },
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { if (searchQuery.isNotEmpty()) onSearch(searchQuery) },
            modifier = modifier.align(Alignment.End)
        ) {
            Text("Search")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error: $message", color = Color.Red)
    }
}

@Composable
fun WordDetailsScreen(
    word: Word,
    onBackPress :() -> Unit,
    modifier: Modifier = Modifier) {
    BackHandler {
        onBackPress.invoke()
    }
    LazyColumn(modifier = modifier.padding(16.dp)) {
        item {
            Text(text = "Word: ${word.word}", style = MaterialTheme.typography.headlineSmall)
            word.phonetics.forEach { phonetic ->
                Text(
                    text = "Phonetic: ${phonetic.text}",
                    style = MaterialTheme.typography.labelLarge
                )
                /*phonetic.audio?.let {
                    AudioPlayer(audioUrl = it) // Custom composable to handle audio playback
                }*/
            }
        }

        items(word.meanings) { meaning ->
            MeaningCard(meaning)
        }
    }
}

@Composable
fun MeaningCard(meaning: Meaning) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Part of Speech: ${meaning.partOfSpeech}", style = MaterialTheme.typography.labelLarge)
            meaning.definitions.forEach { definition ->
                Text(text = "Definition: ${definition.definition}", style = MaterialTheme.typography.bodyLarge)
                definition.example?.let {
                    Text(text = "Example: $it", style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic)
                }
            }
        }
    }
}

/*@Composable
fun AudioPlayer(audioUrl: String) {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }

    LaunchedEffect(audioUrl) {
        mediaPlayer.setDataSource(context, URI.create(audioUrl))
        mediaPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Button(onClick = { mediaPlayer.start() }) {
        Text("Play Audio")
    }
}*/
