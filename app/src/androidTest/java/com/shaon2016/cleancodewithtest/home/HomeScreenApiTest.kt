package com.shaon2016.cleancodewithtest.home

import android.util.Log
import com.google.common.truth.Truth.assertThat
import com.shaon2016.cleancodewithtest.data.Result
import com.shaon2016.cleancodewithtest.data.network.Helper.setResponse
import com.shaon2016.cleancodewithtest.data.remote.IApiService
import com.shaon2016.cleancodewithtest.data.succeeded
import com.shaon2016.cleancodewithtest.di.AppModule
import com.shaon2016.cleancodewithtest.repo.home.HomeRepoImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeScreenApiTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()

    }

    private lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var homeRepoImpl: HomeRepoImpl

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun productSearchMockResponseNotNull() = runBlocking {
        mockWebServer.setResponse("product_search_success_response.json", 200)

        homeRepoImpl.getSearchedProducts("notebook").let { result ->
            result as Result.Success

            assertThat(result.data).isNotNull()

            //Request received by the mock server
            assertThat(result.data.results).hasSize(2)
        }
    }
}































