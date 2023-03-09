package com.example.movieapp.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getMovieLiveData(): LiveData<List<Movie>> = moviesLiveData

    fun listAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.listMovies()
            moviesLiveData.postValue(movies)
        }
    }

}