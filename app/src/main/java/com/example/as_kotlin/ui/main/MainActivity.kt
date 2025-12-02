package com.example.as_kotlin.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.as_kotlin.R
import com.example.as_kotlin.data.MovieRepository
import com.example.as_kotlin.data.model.Movie
import com.example.as_kotlin.ui.adapter.MovieAdapter
import com.example.as_kotlin.ui.details.DetailsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private val movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupRecyclerView()
        loadMovies()
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MovieAdapter(
            onItemClick = { movie -> navigateToDetails(movie) },
            onItemLongClick = { movie -> removeMovie(movie) }
        )

        recyclerView.adapter = adapter
    }

    private fun loadMovies() {
        movieList.clear()
        movieList.addAll(MovieRepository.getMovies())
        adapter.submitList(movieList.toList())
    }

    private fun navigateToDetails(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }

    private fun removeMovie(movie: Movie) {
        movieList.remove(movie)
        adapter.submitList(movieList.toList())
        Toast.makeText(this, getString(R.string.movie_removed, movie.title), Toast.LENGTH_SHORT).show()
    }
}

