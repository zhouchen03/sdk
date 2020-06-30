package com.example.sdk.service

import com.example.sdk.model.ApiUserRS
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): ApiUserRS

}