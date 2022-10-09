package com.pratik.news.articlelist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratik.news.articlelist.data.models.Article
import com.pratik.news.articlelist.repository.INewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val newsRepository: INewsRepository) : ViewModel() {

    private val logTag = ArticlesViewModel::class.java.simpleName
    private val _mutableLiveData = MutableLiveData<List<Article>>()
    val listLiveData: LiveData<List<Article>> = this._mutableLiveData

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e(logTag, "Error fetching Articles", throwable)
    }

    fun fetchArticles() {
        viewModelScope.launch(handler) {
            val articles = newsRepository.fetchArticles()
            _mutableLiveData.postValue(articles)
        }
    }
}
