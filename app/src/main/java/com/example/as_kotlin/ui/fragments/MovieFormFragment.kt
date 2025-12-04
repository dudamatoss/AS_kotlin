package com.example.as_kotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.as_kotlin.databinding.FragmentMovieFormBinding
import com.example.as_kotlin.ui.viewmodel.MovieFormViewModel

class MovieFormFragment : Fragment() {
    private var _binding: FragmentMovieFormBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: MovieFormViewModel by viewModels()
    
    private val movieId: Int
        get() = arguments?.getInt("movieId") ?: 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieFormBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val id = if (movieId > 0) movieId else null
        viewModel.loadMovie(id)
        
        setupObservers()
        setupToolbar()
        setupSaveButton()
    }

    private fun setupObservers() {
        binding.toolbar.title = if (movieId > 0) {
            getString(com.example.as_kotlin.R.string.edit_movie)
        } else {
            getString(com.example.as_kotlin.R.string.add_movie)
        }

        viewModel.saveSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                findNavController().navigateUp()
            }
        })
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupSaveButton() {
        binding.buttonSave.setOnClickListener {
            val title = viewModel.title.get()?.trim() ?: ""
            val director = viewModel.director.get()?.trim() ?: ""
            val yearText = viewModel.releaseYear.get()?.trim() ?: ""
            val synopsis = viewModel.synopsis.get()?.trim() ?: ""

            if (validateInput(title, director, yearText, synopsis)) {
                viewModel.saveMovie()
            }
        }
    }

    private fun validateInput(title: String, director: String, year: String, synopsis: String): Boolean {
        var isValid = true
        
        if (title.isEmpty()) {
            binding.textInputTitle.error = "Título é obrigatório"
            isValid = false
        } else {
            binding.textInputTitle.error = null
        }
        
        if (director.isEmpty()) {
            binding.textInputDirector.error = "Diretor é obrigatório"
            isValid = false
        } else {
            binding.textInputDirector.error = null
        }
        
        if (year.isEmpty() || year.toIntOrNull() == null) {
            binding.textInputYear.error = "Ano inválido"
            isValid = false
        } else {
            binding.textInputYear.error = null
        }
        
        if (synopsis.isEmpty()) {
            binding.textInputSynopsis.error = "Sinopse é obrigatória"
            isValid = false
        } else {
            binding.textInputSynopsis.error = null
        }
        
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

