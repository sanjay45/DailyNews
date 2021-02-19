package com.sanjay.newsappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController


import com.sanjay.newsappmvvm.R
import com.sanjay.newsappmvvm.db.ArticleDatabase
import com.sanjay.newsappmvvm.repository.NewsRepository

import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val database = ArticleDatabase(this)
        val newsRepository = NewsRepository(database)
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        newsViewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}