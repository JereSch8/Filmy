package com.peakycoders.filmy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.peakycoders.filmy.ui.home.HomeActivity
import com.peakycoders.filmy.ui.theme.FilmyTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilmyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen()
                }
            }
        }
    }
    @Composable
    fun SplashScreen(){
        var startAnimation by remember { mutableStateOf(false) }
        val alphaAnim = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
            animationSpec = tween(
                durationMillis = 1000
            )
        )

        LaunchedEffect(key1 = true){
            startAnimation = true
            delay(1000)
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }

        Splash(alpha = alphaAnim.value)


    }

    @Composable
    fun Splash(alpha: Float){
        Box(modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.Black)
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(modifier = Modifier.size(150.dp).alpha(alpha = alpha).align(Alignment.Center).background(
                Color.Black),
                painter = painterResource(id = R.mipmap.ic_launcher_round),
                contentDescription = "FILMY ICON")//, tint = androidx.compose.ui.graphics.Color.White)
        }
    }

}

