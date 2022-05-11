package com.shaon2016.cleancodewithtest.data.network

import com.shaon2016.cleancodewithtest.data.network.Helper.setResponse
import com.shaon2016.cleancodewithtest.data.remote.ApiHelper
import com.shaon2016.cleancodewithtest.data.remote.IApiService
import com.google.common.truth.Truth
import com.shaon2016.cleancodewithtest.data.remote.ApiUrl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ProductDetailTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var service: IApiService

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.start(8000)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun productDetailsMockResponseNotNull() {

        runBlocking {
            mockWebServer.setResponse("product_detail_success_response.json", 200)

            val hashMap = HashMap<String, String>()
            val responseBody = service.getRequest(ApiUrl.productDetailUrl, hashMap).body()

            Truth.assertThat(responseBody).isNotNull()
        }
    }
}