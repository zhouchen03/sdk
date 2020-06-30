package com.example.spoofer.api.handlers

import com.example.spoofer.api.MockedResponse
import com.example.spoofer.api.RequestHandler
import java.net.URI

class GetUserhandler(val uri: URI) : RequestHandler() {

    override val mockedResponse: MockedResponse?
        get() {
            val response =
                if (uri.host.contains("spoofer")) {
                    fromJson("user/user_with_image.json", UserRS::class.java)
                }
                else {
                    fromJson("user/user_no_image.json", UserRS::class.java)
                }
            if (response != null) {
                val body = toJson(response)
                if (body != null) {
                    return MockedResponse(body)
                }
            }
            return null
        }

}
