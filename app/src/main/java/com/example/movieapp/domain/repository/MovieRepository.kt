package com.example.movieapp.domain.repository

import com.example.movieapp.domain.fakeapi.MovieDbApi
import com.example.movieapp.domain.model.Movie

class MovieRepository(
    private val movieDbApi: MovieDbApi
) {
    fun listMovies(): List<Movie> {
        return movieDbApi.getAllMovies()
    }

    fun getMovie(movieId: String): Movie {
        val movies = movieDbApi.getAllMovies()
        val movie = movies.find { it.id.toString() == movieId }
        return movie!!
    }

    fun addMovie(title: String, description: String, director: String, actors: List<String>):List<Movie> {
        movieDbApi.addMovie(title, description, director, actors)
        return movieDbApi.getAllMovies()
    }
}