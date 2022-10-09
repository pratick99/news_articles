package com.pratik.news.articlelist.repository

import com.google.gson.Gson
import com.pratik.news.articlelist.data.source.INewsRemoteDataSource
import com.pratik.news.articlelist.data.models.Article
import com.pratik.news.articlelist.data.models.Result
import javax.inject.Inject

class NewsRepository @Inject constructor(private val remoteDataSource: INewsRemoteDataSource) : INewsRepository {

    override suspend fun fetchArticles(): List<Article> {
        val result = remoteDataSource.fetchNewsData()
        return if (result != null) {
            val gson = Gson()
            val articlesResult = gson.fromJson(result, Result::class.java)
            articlesResult.articles.toList()
        } else {
            emptyList<Article>().toList()
        }
    }
}
