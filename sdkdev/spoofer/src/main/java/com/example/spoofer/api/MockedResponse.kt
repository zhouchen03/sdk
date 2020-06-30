package com.example.spoofer.api

import java.io.ByteArrayInputStream
import java.io.InputStream

class MockedResponse @JvmOverloads constructor(
    private val body: String,
    private val httpStatus: Int = 200,
    private val httpMessage: String = "Response"
)  {

    fun getRawStatusCode(): Int {
        return httpStatus
    }

    fun getBody(): InputStream {
        return ByteArrayInputStream(body.toByteArray())
    }

    fun getBodyString(): String {
        return body
    }

    fun getMessage(): String {
        return httpMessage
    }

}