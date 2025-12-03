package com.example.as_kotlin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val director: String,
    val releaseYear: Int,
    val synopsis: String
) : Serializable
