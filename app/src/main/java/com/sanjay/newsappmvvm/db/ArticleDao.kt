package com.sanjay.newsappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sanjay.newsappmvvm.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertArticle(article : Article) : Long

    @Query("SELECT * FROM  articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}