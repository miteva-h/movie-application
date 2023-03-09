package com.example.movieapp.domain.fakeapi

class MovieDbApiProvider {
    companion object {
        @Volatile
        private var INSTANCE: MovieDbApi? = null

        @JvmStatic
        fun getMovieDbApi(): MovieDbApi {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = createMovieDbApi()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private fun createMovieDbApi(): MovieDbApi {
            var movieDbApi = MovieDbApi()
            movieDbApi.addMovie(
                "Titanic",
                "Romance/Drama",
                "James Cameron",
                mutableListOf("Kate Winslet", "Leonardo DiCaprio", "Billy Zane")
            )
            movieDbApi.addMovie(
                "The Mask of Zorro",
                "Adventure/Action",
                "Martin Campbell",
                mutableListOf("Antonio Banderas", "Catherine Zeta-Jones", "Anthony Hopkins")
            )
            movieDbApi.addMovie(
                "The Da Vinci Code",
                "Mystery/Thriller",
                "Ron Howard",
                mutableListOf("Tom Hanks", "AAudrey Tautou", "Paul Bettany", "Jean Reno")
            )
            return movieDbApi
        }
    }
}