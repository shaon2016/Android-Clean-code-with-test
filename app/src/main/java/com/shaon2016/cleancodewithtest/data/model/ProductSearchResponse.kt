package com.shaon2016.cleancodewithtest.data.model

data class ProductSearchResponse(
    val paging: Paging,
    val results: List<Product>
)

data class Paging(
    val limit: Int,
    val offset: Int,
    val primary_results: Int,
    val total: String
)

data class Product(
    val id: String,
    val price: String,
    val thumbnail: String,
    val title: String
)