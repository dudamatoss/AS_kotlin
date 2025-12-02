package com.example.as_kotlin.data.model

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val director: String,
    val releaseYear: Int,
    val synopsis: String
) : Serializable

