package com.pratik.news.articlelist.repository

import com.pratik.news.articlelist.data.models.Article

interface INewsRepository {

    suspend fun fetchArticles(): List<Article>
}
