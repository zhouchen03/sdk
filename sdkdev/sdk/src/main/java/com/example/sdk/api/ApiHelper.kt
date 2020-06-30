package com.example.sdk.api

import com.example.sdk.api.impl.ApiHelperImpl
import com.example.sdk.model.ApiUser


class ApiHelper {
    private val impl = ApiHelperImpl()

    fun init(test: Boolean) {
        impl.init(test)
    }

    suspend fun getUsers(): List<ApiUser>? {
        return impl.getUsers().users
    }
}