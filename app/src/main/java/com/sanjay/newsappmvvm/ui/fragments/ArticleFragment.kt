package com.sanjay.newsappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sanjay.newsappmvvm.R
import com.sanjay.newsappmvvm.ui.NewsActivity
import com.sanjay.newsappmvvm.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*


class ArticleFragment : Fragment() {

    lateinit var newsViewModel: NewsViewModel
    val args:ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as NewsActivity).newsViewModel
       val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        fab.setOnClickListener {
            newsViewModel.insertArticle(article)
            Snackbar.make(view,"Article Saved Successfully", Snackbar.LENGTH_SHORT).show()
        }
    }


}