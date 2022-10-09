package com.pratik.news.articlelist.data.source

import com.pratik.news.util.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val endPoint: String,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val logger: Logger
) : INewsRemoteDataSource {

    private val logTag = NewsRemoteDataSource::class.java.simpleName

    override suspend fun fetchNewsData(): String? = withContext(dispatcher) {
        try {
            val url = URL(endPoint)
            val httpsURLConnection = url.openConnection()
            val reader = BufferedReader(InputStreamReader(httpsURLConnection.getInputStream()))
            val responseBuilder = StringBuilder()
            var inputLine: String?
            while (reader.readLine().also { inputLine = it } != null) {
                responseBuilder.append(inputLine)
            }
            reader.close()
            return@withContext responseBuilder.toString()
        } catch (exception: IOException) {
            logger.e(logTag, "Error fetching data for Articles ", exception)
        }
        return@withContext null
    }
}
