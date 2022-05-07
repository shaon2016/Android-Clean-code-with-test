package com.shaon2016.cleancodewithtest.repo.home

import com.shaon2016.cleancodewithtest.data.local.ProductDao
import javax.inject.Inject

class HomeLocalRepoImpl @Inject constructor(private val productDao: ProductDao) : HomeLocalRepo {

}