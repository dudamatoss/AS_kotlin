package com.example.as_kotlin.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.as_kotlin.data.dao.MovieDao
import com.example.as_kotlin.data.database.AppDatabase
import com.example.as_kotlin.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(context: Context) {
    private val movieDao: MovieDao = AppDatabase.getDatabase(context).movieDao()

    fun getAllMovies(): LiveData<List<Movie>> = movieDao.getAllMovies()

    suspend fun getMovieById(movieId: Int): Movie? = withContext(Dispatchers.IO) {
        movieDao.getMovieById(movieId)
    }

    suspend fun insertMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.insertMovie(movie)
    }

    suspend fun updateMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.updateMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.deleteMovie(movie)
    }

    suspend fun deleteMovieById(movieId: Int) = withContext(Dispatchers.IO) {
        movieDao.deleteMovieById(movieId)
    }

    suspend fun getMovieCount(): Int = withContext(Dispatchers.IO) {
        movieDao.getMovieCount()
    }

    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getRepository(context: Context): MovieRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = MovieRepository(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
}
