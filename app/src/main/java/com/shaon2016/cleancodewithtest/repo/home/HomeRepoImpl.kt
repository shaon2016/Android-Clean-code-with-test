package com.shaon2016.cleancodewithtest.repo.home

import com.shaon2016.cleancodewithtest.data.Result
import com.shaon2016.cleancodewithtest.data.model.ProductSearchResponse
import javax.inject.Inject


class HomeRepoImpl @Inject constructor(
    private val homeLocalRepo: HomeLocalRepo,
    private val homeNetworkRepo: HomeRemoteRepo
) : HomeRepo {
    override suspend fun getSearchedProducts(query: String): Result<ProductSearchResponse> {
        return homeNetworkRepo.getSearchedProducts(query)
    }
}