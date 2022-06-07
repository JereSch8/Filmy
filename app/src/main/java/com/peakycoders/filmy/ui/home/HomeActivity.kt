package com.peakycoders.filmy.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakycoders.filmy.R
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.theme.FilmyTheme

class HomeActivity : ComponentActivity() {

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val moviesPopular = homeViewModel.popularMovies.value
            val moviesPlayingNow = homeViewModel.nowPlayingMovies.value
            val moviesVisited = homeViewModel.visitedMovies.value
            val isLoading = homeViewModel.isLoading.value

            FilmyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoading) CustomCircularProgressBar()
                    SimpleListView(
                        movieList = moviesPlayingNow,
                        modifier = Modifier
                            .padding(8.dp)
                            .offset(y = 0.dp))
                    SimpleListView(
                        movieList = moviesPopular,
                        modifier = Modifier
                            .padding(8.dp)
                            .offset(y = 250.dp))

                    SimpleListView(
                        movieList = moviesVisited,
                        modifier = Modifier
                            .padding(8.dp)
                            .offset(y = 500.dp))
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleListView(movieList : List<Movie>, modifier: Modifier ) {
    LazyRow(
        modifier = modifier.padding(10.dp),
        contentPadding = PaddingValues(20.dp),
    ) {
        items(movieList){ movie ->
            Card(
                modifier = Modifier.height(240.dp).width(160.dp).padding(20.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, Color.White)

            ) {
                Column() {
                    Card(
                        modifier = Modifier.width(Double.POSITIVE_INFINITY.dp).height(140.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.ic_launcher_background),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Text(text = movie.title, style = TextStyle(fontSize = 16.sp), modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp))
                }
            }

        }
    }
}