package com.sanjay.newsappmvvm.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjay.newsappmvvm.Article
import com.sanjay.newsappmvvm.NewsResponse
import com.sanjay.newsappmvvm.repository.NewsRepository
import com.sanjay.newsappmvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository): ViewModel() {


    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private val breakingNewsPage = 1

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage = 1

    init {
        getBreakingNews("in")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery,searchNewsPage)
        breakingNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())

    }

    fun insertArticle(article: Article) = viewModelScope.launch {
        newsRepository.insert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()


    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.delete(article)
    }
}