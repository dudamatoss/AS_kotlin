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
import com.example.as_kotlin.R
import com.example.as_kotlin.databinding.FragmentMovieDetailsBinding
import com.example.as_kotlin.ui.viewmodel.MovieDetailsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: MovieDetailsViewModel by viewModels()
    
    private val movieId: Int
        get() = arguments?.getInt("movieId") ?: 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel.loadMovie(movieId)
        setupObservers()
        setupToolbar()
        setupMenu()
    }

    private fun setupObservers() {
        viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                binding.movie = it
                binding.toolbar.title = it.title
            }
        })
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupMenu() {
        binding.toolbar.inflateMenu(R.menu.menu_details)
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_edit -> {
                    viewModel.movie.value?.let { movie ->
                        val bundle = Bundle().apply {
                            putInt("movieId", movie.id)
                        }
                        findNavController().navigate(R.id.action_movieDetailsFragment_to_movieFormFragment, bundle)
                    }
                    true
                }
                R.id.action_delete -> {
                    viewModel.movie.value?.let { movie ->
                        showDeleteDialog(movie)
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun showDeleteDialog(movie: com.example.as_kotlin.data.model.Movie) {
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
                findNavController().navigateUp()
            }
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

