package com.example.as_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.as_kotlin.R
import com.example.as_kotlin.data.model.Movie

class MovieAdapter(
    private val onItemClick: (Movie) -> Unit,
    private val onItemLongClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick, onItemLongClick)
    }
}

