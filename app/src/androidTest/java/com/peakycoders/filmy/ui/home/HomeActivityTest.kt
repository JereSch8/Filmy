package com.peakycoders.filmy.ui.home

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.peakycoders.filmy.MainActivity
import com.peakycoders.filmy.ui.patterns.filmTestTag


import com.peakycoders.filmy.ui.theme.FilmyTheme
import kotlinx.coroutines.delay
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class HomeActivityTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

/*
    @Before
    fun setUp(){
        //lanzo homeActivity
        composeTestRule.setContent{
            //HomeActivity()
        }
    }*/
    @Test
    fun app_launch(){
        composeTestRule.onNodeWithTag(hmTestTag).assertIsDisplayed()
    }

    @Test
    fun userScrollsDown() {
        composeTestRule.onNodeWithTag(hmTestTag).performTouchInput {
            this.swipe(
                start = this.center,
                end = Offset(this.center.x, this.center.y - 500),
                durationMillis = 200
            )
        }
        // Check that the jump to bottom button is shown
        findRecientToBottom().assertIsDisplayed()
    }
    @Test
    fun TopAppBarComposeisView(){
        composeTestRule.onNodeWithText("Movies").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Search button").assertIsDisplayed()
    }

/*    @Test
    fun userScrollsRight() {        //se rompio

        composeTestRule.onNodeWithTag(hmTestTag).performTouchInput { //TODO Revisar creo que esta raro
            this.swipe(
                start = this.center,
                end = Offset(this.center.x + 500, this.center.y),
                durationMillis = 200
            )
            composeTestRule.onNodeWithTag(filmTestTag)
                .assertIsNotDisplayed()//assertIsNotDisplayed()
        }
    }*/


    private fun findRecientToBottom() =
       composeTestRule.onNodeWithText("Ultimas visitadas")
}
