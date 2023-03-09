package com.example.movieapp.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.fakeapi.MovieDbApi
import com.example.movieapp.domain.fakeapi.MovieDbApiProvider
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository:MovieRepository) : ViewModel() {

    private val detailsForMovieLiveData = MutableLiveData<Movie>()

    fun getDetailsForMovieLiveData(): LiveData<Movie> = detailsForMovieLiveData

    fun listAllDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.getMovie(movieId)
            detailsForMovieLiveData.postValue(movie)
        }
    }
}