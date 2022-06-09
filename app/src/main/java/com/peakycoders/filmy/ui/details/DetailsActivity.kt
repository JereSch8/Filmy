package com.peakycoders.filmy.ui.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakycoders.filmy.R
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.home.HomeActivity
import com.peakycoders.filmy.ui.theme.FilmyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.peakycoders.filmy.ui.utils.fullScreen
import kotlin.math.truncate


class DetailsActivity : ComponentActivity() {
    private val detailsViewModel : DetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen()

        if(detailsViewModel.movie == null){
            Toast.makeText(
                this,
                resources.getString(R.string.error_movietransfer),
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this@DetailsActivity, HomeActivity::class.java))
        }

        val movie : Movie = detailsViewModel.movie!! //TODO: Aca tienen la peli para cargar toda al vista
        Log.e( "onCreate: ", "moivie: ${movie}")

            setContent {
            FilmyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    display(movie)
                }
            }
        }
    }
    @Composable
    fun display(movie: Movie){
        val scroll = rememberScrollState(0)
        var isExpanded by remember { mutableStateOf(true) }
        LazyColumn{
            item{
                Box(modifier = Modifier.fillMaxWidth()){
                    Column() {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/original/${movie.backdrop_path}", contentDescription =
                            "",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.White))
                        Text(text = movie.title, modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .background(color = Color.White), color = Color.Black,
                            textAlign = TextAlign.Center,
                            style = TextStyle(fontSize = 30.sp))
                        Row(modifier = Modifier
                            .horizontalScroll(scroll)
                            .fillMaxSize()) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/original/${movie.poster_path}", contentDescription =
                            "", modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .border(5.dp, Color.White, CircleShape)
                                .padding(10.dp)
                                .fillMaxSize()
                                .background(color = Color.White)
                                , alignment = Alignment.Center)
                            Column() {
                                Text(text = movie.title, fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Left, modifier = Modifier.padding(10.dp))
                                Text(text = "Fecha de lanzamiento: "+movie.release_date, fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Left, modifier = Modifier.padding(10.dp))
                                Text(text = "Idioma original: "+ movie.original_language.toUpperCase(), fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Left, modifier = Modifier.padding(10.dp))
                                Text(text = "Popularidad: "+truncate(movie.popularity/1000), fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Left, modifier = Modifier.padding(10.dp), fontSize = 15.sp)
                                }
                        }
                        Text(text = movie.overview, modifier = Modifier
                            .clickable { isExpanded = !isExpanded }
                            .padding(20.dp)
                            .offset(y = 0.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 5,
                            overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis)

                    }
                }
            }

        }
    }


}


