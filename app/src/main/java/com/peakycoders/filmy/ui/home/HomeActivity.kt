package com.peakycoders.filmy.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakycoders.filmy.ui.theme.FilmyTheme
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import com.peakycoders.filmy.R
import com.peakycoders.filmy.ui.utils.fullScreen


class HomeActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.fullScreen()

        setContent {
            FilmyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { TopAppBarCompose() },
                        content = { innerPadding ->
                            val scrollState = rememberScrollState()
                            Column(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .verticalScroll(scrollState)
                                    .absolutePadding(bottom = 20.dp)
                            ) {
                                Box(modifier = Modifier.height(20.dp))
                                homeViewModel.responseNowPlaying.value.Get()
                                Subtitle(subtitle = "Populares")
                                homeViewModel.responsePopular.value.Get()
                                Subtitle(subtitle = "Ultimas visitadas")
                                homeViewModel.visitedMovies.value.Get()
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun TopAppBarCompose(){
        SmallTopAppBar(
            title = { Text(
                text = "Movies",
                fontSize = 16.sp,
                style = TextStyle(fontWeight = Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color(resources.getColor(R.color.filmy_color, theme)))
                    },
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search button",
                        tint = Color(resources.getColor(R.color.filmy_color, theme))
                    )
                }
            },
        )
    }

    @Composable
    private fun Subtitle(subtitle : String){
        Box(modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
        ){
            Text(
                text = subtitle,
                modifier = Modifier
                    .absolutePadding(top = 25.dp)
                    .fillMaxSize(),
                fontWeight = Bold,
                fontSize = 20.sp
            )
        }
    }
}
