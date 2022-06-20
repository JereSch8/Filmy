package com.peakycoders.filmy.ui.home


import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class HomeActivityTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()


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




    private fun findRecientToBottom() = composeTestRule.onNodeWithText("Populares")
}
