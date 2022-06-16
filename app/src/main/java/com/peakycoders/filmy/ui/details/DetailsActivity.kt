package com.peakycoders.filmy.ui.details

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakycoders.filmy.R
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.home.HomeActivity
import com.peakycoders.filmy.ui.theme.FilmyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.peakycoders.filmy.ui.utils.Utils
import com.peakycoders.filmy.ui.utils.fullScreen
import androidx.compose.ui.text.style.TextOverflow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
    private val detailsViewModel : DetailsViewModel by viewModels()
    private lateinit var movie : Movie

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

        movie = detailsViewModel.movie!!

            setContent {
                FilmyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Display()
                }
            }
        }
    }

    @Composable
    private fun Display(){
        LazyColumn{
            item{
                Header()
                Title()
                Row(modifier = Modifier.fillMaxSize()) {
                    Poster()
                    Information()
                }
                Overview()
                detailsViewModel.response.value.Get()
            }
        }
    }

    @Composable
    private fun Header(){
        AsyncImage(
            model = Utils.genURL_img("${movie.backdrop_path}"),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        )
    }

    @Composable
    private fun Title(){
        Text(text = movie.title,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 30.sp))
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Poster(){
        Card(
            modifier = Modifier
                .height(200.dp)
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(20.dp)
        ){
            AsyncImage(
                model = Utils.genURL_img("${movie.poster_path}"),
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
        }
    }

    @Composable
    private fun Information(){
        Column {
            Text(
                text = "Fecha de lanzamiento: ${movie.release_date}",
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(5.dp),
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Idioma original: ${movie.original_language.uppercase()}",
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(5.dp),
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Popularidad: ${movie.vote_average}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(5.dp),
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Votos: ${movie.vote_count}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(5.dp),
                overflow = TextOverflow.Ellipsis,
            )
        }
    }

    @Composable
    private fun Overview(){
        var isExpanded by remember { mutableStateOf(false) }
        Text(
            text = movie.overview, modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(20.dp)
                .offset(y = 0.dp),
            maxLines = if (isExpanded) Int.MAX_VALUE else 5,
            overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
        )
    }

}


