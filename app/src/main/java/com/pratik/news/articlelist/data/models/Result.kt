package com.pratik.news.articlelist.data.models

import java.io.Serializable

data class Result(val status: String, val articles: List<Article>) : Serializable
