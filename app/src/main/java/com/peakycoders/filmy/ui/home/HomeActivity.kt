package com.peakycoders.filmy.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.theme.FilmyTheme

class HomeActivity : ComponentActivity() {

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val movies = homeViewModel.popularMovies.value
            val isLoading = homeViewModel.isLoading.value

            FilmyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoading) CustomCircularProgressBar()
                    SimpleListView(movies)
                }
            }
        }
    }
}

@Composable
private fun CustomCircularProgressBar(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            strokeWidth = 6.dp)
    }
}

private val listModifier = Modifier
    .fillMaxSize()
    .padding(10.dp)

private val textStyle = TextStyle(fontSize = 20.sp)

@Composable
fun SimpleListView(movieList : List<Movie>) {
    LazyRow(modifier = listModifier) {
        items(movieList){ movie ->
            Text(text = movie.title, style = textStyle)
        }
    }
}