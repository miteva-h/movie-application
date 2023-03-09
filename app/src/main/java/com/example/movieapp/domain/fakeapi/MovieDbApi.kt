package com.example.movieapp.domain.fakeapi

import com.example.movieapp.domain.model.Movie

class MovieDbApi {

    private val movies: MutableList<Movie> = ArrayList()


    fun getAllMovies(): List<Movie> {
        return movies
    }

    fun addMovie(title: String, description: String, director: String, actors: List<String>) {
        val newMovie = Movie(movies.size, title, description, director, actors)
        movies.add(newMovie)
    }
}