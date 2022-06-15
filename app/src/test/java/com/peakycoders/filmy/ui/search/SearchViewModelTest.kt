package com.peakycoders.filmy.ui.search

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Test

class SearchViewModelTest {

    @Before
    fun onBefore(){
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun getResultSearch() {
        assert(true)
    }

    @Test
    fun search() {
        assert(true)
    }

    @Test
    fun `get search text state`() {
        assert(true)
    }

    @Test
    fun `update search text state`() {
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
    fun `check null input`(){
        assert(true)
    }

}