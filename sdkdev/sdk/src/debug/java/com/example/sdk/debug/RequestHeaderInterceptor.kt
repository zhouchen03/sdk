package com.example.sdk.debug

import com.example.spoofer.api.RequestRouter.getHandler
import okhttp3.*
import java.io.IOException

/**
 * Debug implementation of interceptor for HttpClient which is needed for Spoofer support
 *
 * Created by v-ilevin on 01/04/16.
 */
class RequestHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
        if (url.uri().host.contains("sdk.com")) {
            val response = getHandler(url.uri())?.mockedResponse
            if (null != response) {
                val responseBuilder = Response.Builder()
                    .code(response.getRawStatusCode())
                    .message(response.getMessage())
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(
                        ResponseBody.create(
                            MediaType.parse("application/json"),
                            response.getBodyString()
                        )
                    )
                    .addHeader("content-type", "application/json")
                return responseBuilder.build()

            }
        }
        throw RuntimeException("Implement me!")
    }
}