package com.example.as_kotlin.ui.details

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.as_kotlin.R
import com.example.as_kotlin.data.model.Movie

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setupToolbar()
        displayMovieDetails()
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun displayMovieDetails() {
        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as? Movie ?: return

        supportActionBar?.title = movie.title

        findViewById<TextView>(R.id.movieTitle).text = movie.title
        findViewById<TextView>(R.id.movieDirector).text = movie.director
        findViewById<TextView>(R.id.movieYear).text = movie.releaseYear.toString()
        findViewById<TextView>(R.id.movieSynopsis).text = movie.synopsis
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}

