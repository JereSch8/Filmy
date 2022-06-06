package com.peakycoders.filmy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.peakycoders.filmy.ui.theme.FilmyTheme
import com.peakycoders.filmy.usecases.GetMovieByIDUseCase
import com.peakycoders.filmy.usecases.GetMovieByTitleUseCase
import com.peakycoders.filmy.usecases.GetNowPlayingMovieUseCase
import com.peakycoders.filmy.usecases.GetPopularMovieUseCase
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            val mp = GetPopularMovieUseCase().invoke(1)
            Log.e( "GetPopularMovieUseCase: ", mp.toString())
            val mnp = GetNowPlayingMovieUseCase().invoke()
            Log.e( "GetNowPlayingMovieUseCase: ", mnp.toString())
            val mid = GetMovieByIDUseCase().invoke(338953)
            Log.e( "GetMovieByIDUseCase: ", mid.toString())
            val mtitle = GetMovieByTitleUseCase().invoke("Scoo")
            Log.e( "GetMovieByTitleUseCase: ", mtitle.toString())
        }

        setContent {
            FilmyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Holi")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FilmyTheme {
        Greeting("Android")
    }
}