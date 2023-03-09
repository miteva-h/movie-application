package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import com.example.movieapp.domain.fakeapi.MovieDbApiProvider
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.ui.addnewmovie.AddNewMovieDialogFragment
import com.example.movieapp.ui.movielist.MovieListFragment

class MainActivity : AppCompatActivity(),  AddNewMovieDialogFragment.AddMovieDialogListener {
    private lateinit var repository:MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null){
            supportFragmentManager.commit {
                add(R.id.fragment_container_view, MovieListFragment())
                setReorderingAllowed(true)
            }
        }
        this.repository = MovieRepository(MovieDbApiProvider.getMovieDbApi())
    }

    override fun onDialogPositiveClick(
        title: String,
        description: String,
        director: String,
        actors: ArrayList<String>
    ) {
        repository.addMovie(title, description, director, actors)
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, MovieListFragment())
            setReorderingAllowed(true)
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog?.dismiss()
    }
}