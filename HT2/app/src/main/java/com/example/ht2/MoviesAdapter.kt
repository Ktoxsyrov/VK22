package com.example.ht2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ht2.databinding.MovieItemBinding

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    private var allMovies: MutableList<Movie> = mutableListOf()



    fun setMoviesList(movies: List<Movie>){
        this.allMovies += movies.toMutableList()
        println(allMovies.size)
    }

    fun getMoviesCount(): Int {
        return allMovies.size
    }
    class MyViewHolder(val binding: MovieItemBinding):
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = allMovies[position]
        loadPoster(holder.binding.movieImage, movie.poster)
        holder.binding.totalWows.text= movie.total_wows_in_movie.toString()
    }

    override fun getItemCount(): Int {
        return allMovies.size
    }

    private fun loadPoster(imageView: ImageView, imageUrl: String){
        Glide.with(imageView.context).load(imageUrl)
            .error(R.drawable.ic_baseline_arrow_circle_down_24)
            .into(imageView)
    }
}