package com.example.movieapp.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.adapters.MoviesAdapter
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.ui.addnewmovie.AddNewMovieDialogFragment
import com.example.movieapp.ui.moviedetails.MovieDetailsFragment
import com.example.movieapp.ui.moviedetails.MovieDetailsViewModel

class MovieListFragment : Fragment(R.layout.fragment_movies){

    private var _binding: FragmentMoviesBinding? = null

    private val binding get() = _binding!!

    private lateinit var movieListViewModel: MovieListViewModel

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels{MovieListViewModelFactory(requireContext())}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMoviesBinding.bind(view)

        val viewModelFactory = MovieListViewModelFactory(requireContext())
        movieListViewModel = ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]

        val clicker = object : MoviesAdapter.OnClickListener {
            override fun onClickItem(movieId: String) {
                movieDetailsViewModel.listAllDetails(movieId)
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, MovieDetailsFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }

        var adapter:MoviesAdapter = MoviesAdapter(ArrayList<Movie>(), clicker)
        binding.list.adapter = adapter

        movieListViewModel.getMovieLiveData().observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }

        movieListViewModel.listAll()


        binding.buttonAdd.setOnClickListener {
            AddNewMovieDialogFragment().show(childFragmentManager, "add-movie-dialog")
        }

    }


}
