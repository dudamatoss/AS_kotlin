package com.example.as_kotlin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.as_kotlin.data.MovieRepository
import com.example.as_kotlin.data.model.Movie
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository.getRepository(application)
    
    private val _movie = MutableLiveData<Movie?>()
    val movie: LiveData<Movie?> = _movie

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            _movie.value = repository.getMovieById(movieId)
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }
}

