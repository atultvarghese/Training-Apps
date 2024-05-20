package com.example.jpcomposemvvm.models


data class Movies(
    val page: Int,
    val results: List<Result<Any?>>,
    val total_pages: Int,
    val total_results: Int
)