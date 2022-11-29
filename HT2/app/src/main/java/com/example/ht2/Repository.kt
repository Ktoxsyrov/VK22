package com.example.ht2

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class Repository(private val apiService: ApiService) {
    fun getMovies() = apiService.getMovies()
}