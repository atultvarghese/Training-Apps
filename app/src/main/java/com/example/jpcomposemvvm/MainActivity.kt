package com.example.jpcomposemvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jpcomposemvvm.adapters.MovieAdapter
import com.example.jpcomposemvvm.databinding.ActivityMainBinding
import com.example.jpcomposemvvm.models.Movies
import com.example.jpcomposemvvm.viewmodels.MovieViewModel
import com.example.jpcomposemvvm.viewmodels.getsData

class MainActivity : AppCompatActivity(), getsData {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        binding.viewmodel = viewModel
//        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
//            movieAdapter.setMovieList(movieList)
//        })
    }

    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = movieAdapter
        }
    }

    override fun onFetching() {
        Toast.makeText(this, "Loading..", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(success: LiveData<ArrayList<Movies>>) {
        success.observe(this, Observer { movieList ->
            movieAdapter.setMovieList(movieList)
            Toast.makeText(this, "Value ${success.value}", Toast.LENGTH_SHORT).show()

        })
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, "Error $message", Toast.LENGTH_SHORT).show()
    }
}
