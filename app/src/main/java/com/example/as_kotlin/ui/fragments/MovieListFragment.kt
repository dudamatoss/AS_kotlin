package com.example.as_kotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.as_kotlin.R
import com.example.as_kotlin.data.model.Movie
import com.example.as_kotlin.databinding.FragmentMovieListBinding
import com.example.as_kotlin.ui.adapter.MovieAdapter
import com.example.as_kotlin.ui.viewmodel.MovieListViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupObservers()
        setupFab()
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(
            onItemClick = { movie -> navigateToDetails(movie) },
            onItemLongClick = { movie -> showDeleteDialog(movie) }
        )
        
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.allMovies.observe(viewLifecycleOwner, Observer { movies ->
            adapter.submitList(movies)
            binding.emptyView.visibility = if (movies.isEmpty()) View.VISIBLE else View.GONE
        })
    }

    private fun setupFab() {
        binding.fabAdd.setOnClickListener {
            navigateToForm(null)
        }
    }

    private fun navigateToDetails(movie: Movie) {
        val bundle = Bundle().apply {
            putInt("movieId", movie.id)
        }
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundle)
    }

    private fun navigateToForm(movieId: Int?) {
        val bundle = Bundle().apply {
            putInt("movieId", movieId ?: 0)
        }
        findNavController().navigate(R.id.action_movieListFragment_to_movieFormFragment, bundle)
    }

    private fun showDeleteDialog(movie: Movie) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delete_movie))
            .setMessage(getString(R.string.confirm_delete))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.deleteMovie(movie)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.movie_removed, movie.title),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

