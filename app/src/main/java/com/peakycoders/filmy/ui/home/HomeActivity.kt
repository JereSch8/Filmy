package com.peakycoders.filmy.ui.home


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.details.DetailsActivity
import com.peakycoders.filmy.ui.theme.FilmyTheme

class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val moviesPopular = homeViewModel.popularMovies.value
            val moviesPlayingNow = homeViewModel.nowPlayingMovies.value
            val moviesVisited = homeViewModel.visitedMovies.value
            val isLoading = homeViewModel.isLoading.value

            FilmyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoading) CustomCircularProgressBar()
                    val scrollState = rememberScrollState()
                    Column(modifier = Modifier
                        .verticalScroll(scrollState)
                    )
                    {
                        PlayingNow(movieList = moviesPlayingNow)
                        Box(modifier = Modifier
                            .height(60.dp)
                            .absolutePadding(top = 40.dp)){
                            Text(text = "   Populares")
                        }
                        MoviesPopular(movieList = moviesPopular)
                        Box(modifier = Modifier
                            .height(60.dp)
                            .absolutePadding(top = 40.dp)){
                            Text(text = "   Recien Visitadas")
                        }
                        MoviesVisited(movieList = moviesVisited)
                    }
                }
            }
        }
    }


    @Composable
    @Preview
    private fun CustomCircularProgressBar() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                strokeWidth = 6.dp
            )
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SimpleListView(movieList: List<Movie>) {
        LazyRow(
            contentPadding = PaddingValues(0.dp),
        ) {
            items(movieList) { movie ->
                Column(
                    modifier = Modifier
                        .height(240.dp)
                        .width(160.dp)
                        .padding(5.dp)
                        .background(color = Color.Transparent)
                        .clickable {
                            TransferMovie.movie = movie
                            startActivity(Intent(this@HomeActivity, DetailsActivity::class.java))
                        },

                ) {
                    Column() {
                        Card(
                            modifier = Modifier
                                .width(Double.POSITIVE_INFINITY.dp)
                                .height(200.dp),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/original/${movie.poster_path}",
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Text(
                            text = movie.title,
                            style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                            modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp)
                        )
                    }
                }

            }
        }
    }
    @Composable
    fun PlayingNow(movieList : List<Movie>){
        SimpleListView(movieList)
    }
    @Composable
    fun MoviesPopular(movieList : List<Movie>){
        SimpleListView(movieList)
    }
    @Composable
    fun MoviesVisited(movieList : List<Movie>){
        SimpleListView(movieList)
    }
}
