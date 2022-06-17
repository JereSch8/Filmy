package com.peakycoders.filmy.ui.patterns

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


interface Status {
    @Composable
    fun Get()

    fun name() : String
}

class Success(private val success: TypeSuccess) : Status {
    @Composable
    override fun Get()  {
        success.Show()
    }

    override fun name() = "success:${success.name()}"
}

class Error(private val error : String) : Status {
    @Composable
    override fun Get()  {
        return Text(error,
            color = Color.Red,
            style = TextStyle(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(40.dp))
    }

    override fun name() = "error:$error"
}

class Loading : Status {
    @Composable
    override fun Get()  {
        return Row(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)){
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
    }

    override fun name() = "loading"
}

class Response(private var statusResponse: Status) {
    @Composable
    fun Get() = statusResponse.Get()

    override fun toString() : String = statusResponse.name()
}