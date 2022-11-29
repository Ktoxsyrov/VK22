package com.example.ht2

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://owen-wilson-wow-api.herokuapp.com/wows/"

interface ApiService {

    @GET("random")
    fun getMovies(
        @Query("results") results: Int = 20
    ): Call<List<Movie>>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
            }.build()
            return retrofit.create(ApiService::class.java)
        }

    }
}

