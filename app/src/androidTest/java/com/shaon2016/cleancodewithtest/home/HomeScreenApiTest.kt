package com.shaon2016.cleancodewithtest.home

import com.google.common.truth.Truth.assertThat
import com.shaon2016.cleancodewithtest.data.Result
import com.shaon2016.cleancodewithtest.data.network.BaseApiTest
import com.shaon2016.cleancodewithtest.data.network.Helper.setResponse
import com.shaon2016.cleancodewithtest.repo.home.HomeRepoImpl
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeScreenApiTest : BaseApiTest() {

    @Inject
    lateinit var homeRepoImpl: HomeRepoImpl

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

    @Test
    fun productSearchMockResponseHasSize2() = runBlocking {
        mockWebServer.setResponse("product_search_success_response.json", 200)

        homeRepoImpl.getSearchedProducts("notebook").let { result ->
            result as Result.Success

            assertThat(result.data.results).hasSize(2)
        }
    }

    @Test
    fun productSearchMockResponseShouldBeNull() = runBlocking {
        mockWebServer.setResponse("product_search_no_data_response.json", 200)

        homeRepoImpl.getSearchedProducts("notebook").let { result ->
            result as Result.Success
            assertThat(result.data.results).isNull()
            assertThat(result.data.paging).isNull()
        }
    }

    @Test
    fun productSearchMockResponseShouldBeServerError() = runBlocking {
        mockWebServer.setResponse("product_search_success_response.json", 500)

        homeRepoImpl.getSearchedProducts("notebook").let { result ->
            result as Result.Error

            assertThat(result.exception).isNotNull()
        }
    }
}































