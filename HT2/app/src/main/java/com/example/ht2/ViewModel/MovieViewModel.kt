package com.example.ht2.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ht2.Movie
import com.example.ht2.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val repository: Repository) : ViewModel() {
    val movieList = MutableLiveData<List<Movie>>()
    val error = MutableLiveData<String>()
    fun getAllMovies() {

        val response = repository.getMovies()
        response.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                println(error.value)
                error.postValue("Failed")

            }
        })
    }

}