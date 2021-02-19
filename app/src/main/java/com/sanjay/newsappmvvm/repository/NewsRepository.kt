package com.sanjay.newsappmvvm.repository

import com.sanjay.newsappmvvm.Article
import com.sanjay.newsappmvvm.api.RetrofitInstance
import com.sanjay.newsappmvvm.db.ArticleDatabase


class NewsRepository(
        val db: ArticleDatabase
) {

     suspend fun getBreakingNews(countryCode: String, pageNumber:Int) =
            RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String,pageNumber: Int) =
            RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun insert(article: Article) = db.getArticleDao().insertArticle(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun delete(article: Article) = db.getArticleDao().deleteArticle(article)
}