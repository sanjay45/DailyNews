package com.sanjay.newsappmvvm

import com.sanjay.newsappmvvm.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)