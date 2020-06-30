package com.example.spoofer.api

import com.example.spoofer.api.handlers.GetUserhandler
import java.net.URI

object RequestRouter {
    fun getHandler(uri: URI): RequestHandler? {
        val path = uri.path
        return if (path.startsWith("/users")) {
            GetUserhandler(uri)
        } else null
    }
}