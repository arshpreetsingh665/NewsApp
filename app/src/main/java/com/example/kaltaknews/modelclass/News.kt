package com.example.kaltaknews.modelclass

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)