package com.shaon2016.cleancodewithtest.repo.home

import com.shaon2016.cleancodewithtest.data.Result
import com.shaon2016.cleancodewithtest.data.model.ProductSearchResponse

interface HomeRepo {
    suspend fun getSearchedProducts(query : String): Result<ProductSearchResponse>
}