package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.domain.model.Movie

class MoviesAdapter(
    private val movies: ArrayList<Movie> = ArrayList(),
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    interface OnClickListener {
        fun onClickItem(movieId: String)
    }

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieId: TextView = view.findViewById(R.id.movie_id)
        private var movieTitle: TextView = view.findViewById(R.id.movie_title)
        private var movieDirector: TextView = view.findViewById(R.id.movie_director)

        fun bind(
            movie: Movie,
            onClickListener: OnClickListener
        ) {
            movieId.text = movie.id.toString()
            movieTitle.text = movie.title
            movieDirector.text = movie.director
            itemView.setOnClickListener {
                onClickListener.onClickItem(movieId.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_layout, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        if (movies != null) {
            this.movies.addAll(movies)
        }
        notifyDataSetChanged()
    }
}
