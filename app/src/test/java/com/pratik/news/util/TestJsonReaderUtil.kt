package com.pratik.news.util

import java.io.BufferedReader
import java.io.InputStreamReader

object TestJsonReaderUtil {

    fun getTestResponse(jsonFileName: String): String {
        val inputStream = javaClass
            .classLoader
            ?.getResourceAsStream(jsonFileName)
        val stringBuilder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var inputLine: String?
        while (reader.readLine().also { inputLine = it } != null) {
            stringBuilder.append(inputLine)
        }
        return stringBuilder.toString()
    }
}
