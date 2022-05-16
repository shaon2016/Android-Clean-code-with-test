package com.shaon2016.cleancodewithtest.repo.home

import com.google.gson.Gson
import com.shaon2016.cleancodewithtest.data.Result
import com.shaon2016.cleancodewithtest.data.model.ProductSearchResponse
import com.shaon2016.cleancodewithtest.data.remote.ApiHelper
import com.shaon2016.cleancodewithtest.data.remote.ApiUrl
import com.shaon2016.cleancodewithtest.repo.BaseRepo
import javax.inject.Inject

class HomeRemoteRepoImpl @Inject constructor(private val apiHelper: ApiHelper) : BaseRepo(),
    HomeRemoteRepo {

    override suspend fun getSearchedProducts(query: String): Result<ProductSearchResponse> {
        val map = HashMap<String, String>()
        map["q"] = query

        return try {
            val result =
                apiHelper.getApiCallObservable(ApiHelper.CALL_TYPE_GET, ApiUrl.productSearchUrl, map)

            if (result.isSuccessful) {
                val response =
                    Gson().fromJson(result.body()?.string(), ProductSearchResponse::class.java)

                Result.Success(response)
            } else
                errorHandler(arrayListOf())
        } catch (e: Exception) {
            e.printStackTrace()
            errorHandler(arrayListOf(""), isServerError = true)
        }
    }

}