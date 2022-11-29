package com.example.ht2.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ht2.ApiService
import com.example.ht2.MoviesAdapter
import com.example.ht2.Repository
import com.example.ht2.ViewModel.MovieViewModel
import com.example.ht2.ViewModel.ViewModelFactory
import com.example.ht2.databinding.MoviesFragmentBinding

class MoviesFragment : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var binding: MoviesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoviesFragmentBinding.inflate(inflater)
        recycler = binding.moviesRecycler

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(Repository(apiService = ApiService.create()))
        )
            .get(MovieViewModel::class.java)
        val adapter = MoviesAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

//        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy < 0 && adapter.getMoviesCount()!=0) {
//                    viewModel.getAllMovies()
//                }
//            }
//        })

        binding.retryIcon.setOnClickListener {
            viewModel.error.postValue("Trying") //?
            println(viewModel.error.value)
            binding.retryIcon.visibility = View.GONE
            binding.progressCircular.visibility = View.VISIBLE
            viewModel.getAllMovies()
        }

        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.setMoviesList(it)
            if (it.isNotEmpty())
                binding.progressCircular.visibility = View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it == "Failed")
                binding.retryIcon.visibility = View.VISIBLE
            binding.progressCircular.visibility = View.GONE

        }

        viewModel.getAllMovies()
    }
}