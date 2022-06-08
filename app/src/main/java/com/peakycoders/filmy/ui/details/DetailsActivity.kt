package com.peakycoders.filmy.ui.details

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakycoders.filmy.R
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.home.HomeActivity
import com.peakycoders.filmy.ui.theme.FilmyTheme

class DetailsActivity : ComponentActivity() {
    private val detailsViewModel : DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(detailsViewModel.movie == null){
            Toast.makeText(
                this,
                resources.getString(R.string.error_movietransfer),
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this@DetailsActivity, HomeActivity::class.java))
        }

        val movie : Movie = detailsViewModel.movie!! //TODO: Aca tienen la peli para cargar toda al vista

        setContent {
            FilmyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text = movie.title, modifier = Modifier.padding(20.dp), style = TextStyle(fontSize = 30.sp))
                    Text(text = movie.overview, modifier = Modifier.padding(40.dp).offset(y = 140.dp) )
                }
            }
        }
    }
   

}

