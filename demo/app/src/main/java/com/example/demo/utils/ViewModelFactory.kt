package com.example.demo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demo.ui.main.MainViewModel
import com.example.sdk.api.ApiHelper

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                apiHelper
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}