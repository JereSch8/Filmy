@file:OptIn(ExperimentalMaterial3Api::class)

package com.peakycoders.filmy.ui.patterns

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.entities.models.Cast
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.details.DetailsActivity
import com.peakycoders.filmy.ui.utils.Utils

interface TypeSuccess {
    @Composable
    fun Show()
}

class SuccessCast(private val casting: List<Cast>) : TypeSuccess {
    @Composable
    override fun Show()  {
        val context = LocalContext.current
        return LazyRow(
            modifier = Modifier.height(250.dp),
        ) {
            items(casting) { cast ->
                Column(
                    modifier = Modifier
                        .height(240.dp)
                        .width(160.dp)
                        .padding(5.dp)
                        .background(color = Color.Transparent)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    cast.character,
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                ) {
                    Column {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            AsyncImage(
                                model = Utils.genURL_img(cast.profile_path),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Text(
                            text = cast.original_name,
                            style = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 8.dp, start = 1.dp, end = 1.dp)
                        )
                    }
                }

            }
        }
    }
}

class SuccessMovie(private val movieList: List<Movie>) : TypeSuccess {
    @Composable
    override fun Show() {
        val context = LocalContext.current
        LazyRow {
            items(movieList) { movie ->
                Column(
                    modifier = Modifier
                        .height(240.dp)
                        .width(160.dp)
                        .padding(5.dp)
                        .background(color = Color.Transparent)
                        .clickable {
                            TransferMovie.movie = movie
                            context.startActivity(Intent(context, DetailsActivity::class.java))
                        },
                    ) {
                    Column {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            AsyncImage(
                                model = Utils.genURL_img(movie.poster_path),
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
}

class SuccessSearchMovie(private val movieList: List<Movie>) : TypeSuccess {
    @Composable
    override fun Show() {
        val context = LocalContext.current
        LazyColumn {
            items(movieList) { movie ->
                Row(
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(color = Color.Transparent)
                        .clickable {
                            TransferMovie.movie = movie
                            context.startActivity(Intent(context, DetailsActivity::class.java))
                        },
                ) {
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .height(200.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        AsyncImage(
                            model = Utils.genURL_img(movie.poster_path),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Column{
                        Text(
                            text = movie.title,
                            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
                        )
                        Text(
                            text = movie.overview,
                            style = TextStyle(fontSize = 16.sp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 5,
                            modifier = Modifier.padding(top = 12.dp, start = 8.dp, end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}