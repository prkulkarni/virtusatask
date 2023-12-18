package com.example.virtusatask

import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    open fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
    }

    companion object {
        const val MOVIE_ID_KEY = "movieId"
        const val MOVIE_ID_VAL = "1234"
        const val DEFAULT_ERROR_MESSAGE = "Something went wrong"
    }
}