package com.example.as_kotlin.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.as_kotlin.data.MovieRepository
import com.example.as_kotlin.data.model.Movie
import kotlinx.coroutines.launch

class MovieFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository.getRepository(application)
    
    val title = ObservableField<String>("")
    val director = ObservableField<String>("")
    val releaseYear = ObservableField<String>("")
    val synopsis = ObservableField<String>("")

    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean> = _saveSuccess

    private var currentMovieId: Int = 0

    fun loadMovie(movieId: Int?) {
        currentMovieId = movieId ?: 0
        if (currentMovieId > 0) {
            viewModelScope.launch {
                val movie = repository.getMovieById(currentMovieId)
                movie?.let {
                    title.set(it.title)
                    director.set(it.director)
                    releaseYear.set(it.releaseYear.toString())
                    synopsis.set(it.synopsis)
                }
            }
        } else {
            title.set("")
            director.set("")
            releaseYear.set("")
            synopsis.set("")
        }
    }

    fun saveMovie() {
        val titleValue = title.get() ?: ""
        val directorValue = director.get() ?: ""
        val yearValue = releaseYear.get() ?: "0"
        val synopsisValue = synopsis.get() ?: ""

        if (titleValue.isEmpty() || directorValue.isEmpty() || synopsisValue.isEmpty()) {
            return
        }

        val year = yearValue.toIntOrNull() ?: 0

        viewModelScope.launch {
            val movieToSave = if (currentMovieId > 0) {
                Movie(
                    id = currentMovieId,
                    title = titleValue,
                    director = directorValue,
                    releaseYear = year,
                    synopsis = synopsisValue
                )
            } else {
                Movie(
                    title = titleValue,
                    director = directorValue,
                    releaseYear = year,
                    synopsis = synopsisValue
                )
            }
            
            if (currentMovieId > 0) {
                repository.updateMovie(movieToSave)
            } else {
                repository.insertMovie(movieToSave)
            }
            
            _saveSuccess.value = true
        }
    }
}
