package com.example.spoofer.api

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.io.IOException

abstract class RequestHandler {
    private var httpMethod: String? = null
    private var mContentType: String? = null
    abstract val mockedResponse: MockedResponse?

    protected fun <N> fromJson(resName: String, valueType: Class<N>): N? {
        return try {
            val classLoader = javaClass.classLoader
            val inputStream = classLoader.getResourceAsStream(resName)
            val objectMapper = ObjectMapper()
            objectMapper.readValue(inputStream, valueType)
        } catch (e: IOException) {
            null
        }
    }

    protected fun <N> toJson(value: N): String? {
        return try {
            val objectMapper = ObjectMapper()
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
            objectMapper.writeValueAsString(value)
        } catch (e: JsonProcessingException) {
            null
        }
    }

    fun setMethod(method: String?) {
        httpMethod = method
    }

    fun setContentType(mediaType: String?) {
        mContentType = mediaType
    }

}