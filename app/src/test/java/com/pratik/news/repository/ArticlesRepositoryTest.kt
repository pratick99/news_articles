@file:OptIn(ExperimentalCoroutinesApi::class)

package com.pratik.news.repository

import com.pratik.news.articlelist.data.source.INewsRemoteDataSource
import com.pratik.news.articlelist.data.models.Article
import com.pratik.news.articlelist.repository.INewsRepository
import com.pratik.news.articlelist.repository.NewsRepository
import com.pratik.news.util.TestJsonReaderUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticlesRepositoryTest {

    private lateinit var articleRepository: INewsRepository

    @Mock
    private lateinit var remoteDataSource: INewsRemoteDataSource

    @Before
    fun setup() {
        articleRepository = NewsRepository(remoteDataSource)
    }

    @Test
    fun successful_fetching_articles_data_from_data_source_shouldReturn_() = runTest {
        given(remoteDataSource.fetchNewsData()).willReturn(TestJsonReaderUtil.getTestResponse("ArticlesResponse.json"))
        val result = articleRepository.fetchArticles()
        assertNotNull(result)
        assertEquals(result[0].author, "Alex Wilhelm")
        assertEquals(result[0].description, "Hello and welcome back to our regular morning look at private companies, public markets and the gray space in between. Today we’re exploring some fascinating data from Silicon Valley Bank markets report for Q1 2020. We’re digging into two charts that deal wit…")
    }

    @Test
    fun failure_fetching_articles_data_from_data_source_shouldReturn_emptyList() = runTest {
        given(remoteDataSource.fetchNewsData()).willReturn(null)
        val result = articleRepository.fetchArticles()
        assertEquals(result, emptyList<Article>())
    }
}
