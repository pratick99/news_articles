package com.pratik.news.data

import com.google.gson.Gson
import com.pratik.news.articlelist.data.source.INewsRemoteDataSource
import com.pratik.news.articlelist.data.source.NewsRemoteDataSource
import com.pratik.news.articlelist.data.models.Result
import com.pratik.news.util.TestJsonReaderUtil
import com.pratik.news.util.UnitTestLogger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ArticleListRemoteDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var remoteDataSource: INewsRemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8081)
        val url = mockWebServer.url("").toString()
        remoteDataSource = NewsRemoteDataSource(url, UnconfinedTestDispatcher(), UnitTestLogger())
    }

    @Test
    fun successfulFetchArticlesTest() = runTest {
        enqueueResponse(true)
        val result = remoteDataSource.fetchNewsData()
        assertTrue(result != null)
        val gson = Gson()
        val articlesResult = gson.fromJson(result, Result::class.java)
        assertTrue(articlesResult.articles.isNotEmpty())
    }

    @Test
    fun failureFetchArticlesTest() = runTest {
        enqueueResponse(false)
        val result = remoteDataSource.fetchNewsData()
        assertTrue(result == null)
    }

    private fun enqueueResponse(successFullResponse: Boolean) {
        if (successFullResponse) {
            mockWebServer.enqueue(
                MockResponse().setResponseCode(200).setBody(TestJsonReaderUtil.getTestResponse("ArticlesResponse.json"))
            )
        } else {
            mockWebServer.enqueue(MockResponse().setResponseCode(400))
        }
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }
}
