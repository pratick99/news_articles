package com.pratik.news.articlelist.data.source

interface INewsRemoteDataSource {
    suspend fun fetchNewsData(): String?
}
