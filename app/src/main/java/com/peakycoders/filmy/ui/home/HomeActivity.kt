package com.peakycoders.filmy.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakycoders.filmy.R
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.details.DetailsActivity
import com.peakycoders.filmy.ui.theme.FilmyTheme

class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val moviesPopular = homeViewModel.popularMovies.value //Returns a list of movies
            val moviesPlayingNow = homeViewModel.nowPlayingMovies.value
            val moviesVisited = homeViewModel.visitedMovies.value
            val isLoading = homeViewModel.isLoading.value

            FilmyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoading) CustomCircularProgressBar() //Si isLoading == true, pone en pantalla
                    //la barra de progreso circular que denota al proceso de carga
                    ScaffoldCompose()
                    SimpleListView(
                        movieList = moviesPlayingNow,
                        modifier = Modifier
                            .padding(8.dp)
                            .offset(y = 0.dp)
                    )
                    SimpleListView(
                        movieList = moviesPopular,
                        modifier = Modifier
                            .padding(8.dp)
                            .offset(y = 250.dp)
                    )

                    SimpleListView(
                        movieList = moviesVisited,
                        modifier = Modifier
                            .padding(8.dp)
                            .offset(y = 500.dp)
                    )
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

    /*
    * SimpleListView crea una LazyRow con la ventaja de compone solamente los items visibles.
    */
    @OptIn(ExperimentalMaterial3Api::class)
    //@Preview
    @Composable
    fun SimpleListView(movieList: List<Movie>, modifier: Modifier) {
        LazyRow(
            modifier = modifier.padding(10.dp),
            contentPadding = PaddingValues(20.dp),
        ) {
            items(movieList) { movie ->
                Card(
                    modifier = Modifier
                        .height(240.dp)
                        .width(160.dp)
                        .padding(20.dp)
                        .clickable {
                            TransferMovie.movie = movie
                            startActivity(Intent(this@HomeActivity, DetailsActivity::class.java))
                        },
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, Color.White),

                    ) {
                    Column() {
                        Card(
                            modifier = Modifier
                                .width(Double.POSITIVE_INFINITY.dp)
                                .height(140.dp),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Image(
                                painterResource(R.mipmap.ic_launcher),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Text(
                            text = movie.title,
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp)
                        )
                    }
                }

            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldCompose(){
        Scaffold (
            topBar = {TopAppBarCompose()}
           ){}
    }

    @Composable
    fun ContentCompose(){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White))
            }

    @Composable
    fun TopAppBarCompose(){
        val context = LocalContext.current
        TopAppBar (
            title = { Text(
                text = "Search what you wanna watch",
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )},
            navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(context,"Menu", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = {
                    Toast.makeText(context,"Search", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }

            },
            backgroundColor = Color.White,
            contentColor = Color.Green)

        }


}