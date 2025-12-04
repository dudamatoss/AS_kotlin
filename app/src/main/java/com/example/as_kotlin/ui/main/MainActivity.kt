package com.example.as_kotlin.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.as_kotlin.R
import com.example.as_kotlin.data.MovieRepository
import com.example.as_kotlin.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initializeDatabase()
    }

    private fun initializeDatabase() {
        val repository = MovieRepository.getRepository(this)
        
        lifecycleScope.launch(Dispatchers.IO) {
            val count = repository.getMovieCount()
            
            if (count == 0) {
                val defaultMovies = listOf(
                    Movie(
                        title = "O Poderoso Chefão",
                        director = "Francis Ford Coppola",
                        releaseYear = 1972,
                        synopsis = "A saga da família Corleone, uma das mais poderosas famílias da máfia italiana em Nova York."
                    ),
                    Movie(
                        title = "Pulp Fiction",
                        director = "Quentin Tarantino",
                        releaseYear = 1994,
                        synopsis = "Histórias entrelaçadas de criminosos, boxeadores e outros personagens em Los Angeles."
                    )
                )
                
                defaultMovies.forEach { movie ->
                    repository.insertMovie(movie)
                }
            }
        }
    }
}
