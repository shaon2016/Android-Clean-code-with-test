package com.example.androidtestpractice.data.network

import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidtestpractice.MyApp
import okhttp3.mockwebserver.MockResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

object Helper {
    fun getRetrofit(mockServer : MockWebServer) = Retrofit.Builder()
        .baseUrl(mockServer.url("/")) // mock url
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Sets which response the [MockWebServer] should return when a request is made
     */
    fun MockWebServer.setResponse(fileName: String, responseCode: Int = 200) {
        enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(readFileFromAssets(fileName))
        )
    }

    /**
     * The  file in the [filePath] and return its content as a [String]
     */
    private fun getFileAsString(filePath: String): String {
        val inputStream = ClassLoader.getSystemResourceAsStream(filePath)
        val source = inputStream.source().buffer()

        return source.readString(Charsets.UTF_8)
    }

    private fun readFileFromAssets(fileName: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as MyApp).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}