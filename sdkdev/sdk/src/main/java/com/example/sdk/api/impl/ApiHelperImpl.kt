package com.example.sdk.api.impl

import com.example.sdk.BuildConfig
import com.example.sdk.debug.RequestHeaderInterceptor
import com.example.sdk.service.ApiService
import com.example.sdk.service.RetrofitBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ApiHelperImpl()  {

    val TEST_BASE_URL = "https://spoofer.sample.sdk.com/"
    val PROD_BASE_URL = "https://com.sample.sdk.com/"

    private lateinit var apiService: ApiService

    fun init(test: Boolean) {
        val url = if(test) {  TEST_BASE_URL } else { PROD_BASE_URL}
        val retrofitBuilder = RetrofitBuilder.getRetrofitBuilder(url)
        apiService = if (BuildConfig.DEBUG && test) {
            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(RequestHeaderInterceptor())
            val retrofit: Retrofit = retrofitBuilder.client(httpClientBuilder.build()).build()
            retrofit.create(ApiService::class.java)

        } else {
            retrofitBuilder.build().create(ApiService::class.java)
        }
    }

    suspend fun getUsers() = apiService.getUsers()

}