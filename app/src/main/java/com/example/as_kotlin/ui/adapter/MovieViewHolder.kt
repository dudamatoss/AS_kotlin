package com.example.as_kotlin.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.as_kotlin.R
import com.example.as_kotlin.data.model.Movie

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.movieTitle)
    private val directorTextView: TextView = itemView.findViewById(R.id.movieDirector)
    private val yearTextView: TextView = itemView.findViewById(R.id.movieYear)

    fun bind(movie: Movie, onItemClick: (Movie) -> Unit, onItemLongClick: (Movie) -> Unit) {
        titleTextView.text = movie.title
        directorTextView.text = movie.director
        yearTextView.text = movie.releaseYear.toString()

        itemView.setOnClickListener {
            onItemClick(movie)
        }

        itemView.setOnLongClickListener {
            onItemLongClick(movie)
            true
        }
    }
}

