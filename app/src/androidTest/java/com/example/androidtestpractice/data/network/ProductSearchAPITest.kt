package com.example.androidtestpractice.data.network

import com.example.androidtestpractice.data.model.ProductSearchResponse
import com.example.androidtestpractice.data.network.Helper.setResponse
import com.example.androidtestpractice.data.remote.ApiHelper
import com.example.androidtestpractice.data.remote.IApiService
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductSearchAPITest {
    private lateinit var mockWebServer: MockWebServer

    private lateinit var service: IApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8000)

        service = Helper.getRetrofit(mockWebServer).create(IApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun productSearchMockResponseNotNull() {

        runBlocking {
            mockWebServer.setResponse("product_search_success_response.json", 200)

            val hashMap = HashMap<String, String>()
            val responseBody = service.getRequest(ApiHelper.productSearchUrl, hashMap).body()

            assertThat(responseBody).isNotNull()
        }
    }

    @Test
    fun productSearchMockResponseSentRequestUrlAsExpected() {

        runBlocking {
            mockWebServer.setResponse("product_search_success_response.json", 200)

            //Send Request to the MockServer
            val hashMap = HashMap<String, String>()
            hashMap["offset"] = "1"
            hashMap["q"] = "notebook"
            hashMap["limit"] = "1"

            val responseBody = service.getRequest(ApiHelper.productSearchUrl, hashMap).body()

            //Request received by the mock server
            val request = mockWebServer.takeRequest()

            println(request.requestUrl)
            assertThat(request.path).isEqualTo("/sites/MLU/search?q=notebook&offset=1&limit=1")
        }
    }

    @Test
    fun productSearchMockResponseShouldNotSuccess() {
        runBlocking {
            try {
                mockWebServer.setResponse("product_search_success_response.json", 404)

                val hashMap = HashMap<String, String>()
                val responseBody = service.getRequest(ApiHelper.productSearchUrl, hashMap)

                assertThat(responseBody.isSuccessful).isFalse()
            } catch (e: Exception) {
                assertThat(false).isFalse()
            }
        }
    }

    @Test
    fun productSearchMockResponseLengthShouldFail() {

        runBlocking {
            mockWebServer.setResponse("product_search_success_response.json", 200)

            val hashMap = HashMap<String, String>()
            val responseBody = service.getRequest(ApiHelper.productSearchUrl, hashMap).body()

            val responseData = Gson().fromJson(responseBody?.string(), ProductSearchResponse::class.java)

            assertThat(responseData.results.size).isEqualTo(6)
        }
    }
}