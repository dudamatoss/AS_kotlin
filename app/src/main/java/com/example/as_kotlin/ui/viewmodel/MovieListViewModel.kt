package com.example.as_kotlin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.as_kotlin.data.MovieRepository
import com.example.as_kotlin.data.model.Movie
import kotlinx.coroutines.launch

class MovieListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository.getRepository(application)
    
    val allMovies: LiveData<List<Movie>> = repository.getAllMovies()

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }
}

