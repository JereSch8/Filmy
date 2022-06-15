package com.peakycoders.filmy.ui.home


import com.peakycoders.filmy.ui.details.DetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    @Before
    fun onBefore(){
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get visited movies`() {
        assert(true)
    }

    @Test
    fun `get response popular`() {
        assert(true)
    }

    @Test
    fun `get response now playing`() {
        assert(true)
    }

    @Test
    fun `get update from singleton`() {
        assert(true)
    }

    @Test
    fun `check Success Response`(){
        assert(true)
    }

    @Test
    fun `check Loading Response`(){
        assert(true)
    }

    @Test
    fun `check Error Response`(){
        assert(true)
    }

    @Test
    fun `check attach viewmodel to movieDB changes`(){
        assert(true)
    }

}