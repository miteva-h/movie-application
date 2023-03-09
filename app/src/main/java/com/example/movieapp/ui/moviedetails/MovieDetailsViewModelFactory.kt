package com.example.movieapp.ui.moviedetails

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.fakeapi.MovieDbApiProvider
import com.example.movieapp.domain.repository.MovieRepository

class MovieDetailsViewModelFactory(private val context: Context) : ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(
                MovieRepository(MovieDbApiProvider.getMovieDbApi())
            )
    }
}