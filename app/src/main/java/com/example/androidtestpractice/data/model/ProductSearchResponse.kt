package com.example.androidtestpractice.data.model

data class ProductSearchResponse(
    val paging: Paging,
    val results: List<Result>
)

data class Paging(
    val limit: Int,
    val offset: Int,
    val primary_results: Int,
    val total: String
)

data class Result(
    val id: String,
    val price: String,
    val thumbnail: String,
    val title: String
)