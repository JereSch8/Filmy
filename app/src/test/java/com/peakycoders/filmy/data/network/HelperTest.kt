package com.peakycoders.filmy.data.network

import org.junit.Assert
import org.junit.Test

class HelperTest {
    @Test
    fun `is a singleton instance`() {
        val helper = Helper
        val helper2 = Helper
        Assert.assertEquals(true, helper == helper2)
    }

    @Test
    fun `baseURL created successful`() {
        val helper = Helper
        Assert.assertEquals(Helper.baseURL, helper.getRetrofit().baseUrl().toString())
    }

    @Test
    fun `baseURL created successful NoPass`() {
        val helper = Helper
        Assert.assertEquals("", helper.getRetrofit().baseUrl().toString())
    }
    
}
