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
                    ),
                    Movie(
                        title = "A Lista de Schindler",
                        director = "Steven Spielberg",
                        releaseYear = 1993,
                        synopsis = "A história real de Oskar Schindler, um empresário alemão que salvou mais de mil judeus durante o Holocausto."
                    ),
                    Movie(
                        title = "O Senhor dos Anéis: A Sociedade do Anel",
                        director = "Peter Jackson",
                        releaseYear = 2001,
                        synopsis = "Um hobbit embarca em uma jornada épica para destruir um anel mágico e salvar a Terra Média."
                    ),
                    Movie(
                        title = "Forrest Gump",
                        director = "Robert Zemeckis",
                        releaseYear = 1994,
                        synopsis = "A vida extraordinária de Forrest Gump, um homem simples que vive momentos históricos dos Estados Unidos."
                    ),
                    Movie(
                        title = "Matrix",
                        director = "Lana Wachowski, Lilly Wachowski",
                        releaseYear = 1999,
                        synopsis = "Um programador descobre que o mundo em que vive é uma simulação computacional."
                    ),
                    Movie(
                        title = "Clube da Luta",
                        director = "David Fincher",
                        releaseYear = 1999,
                        synopsis = "Um funcionário de escritório insone forma um clube de luta clandestino como forma de terapia."
                    ),
                    Movie(
                        title = "Interestelar",
                        director = "Christopher Nolan",
                        releaseYear = 2014,
                        synopsis = "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço em busca de um novo lar para a humanidade."
                    ),
                    Movie(
                        title = "A Origem",
                        director = "Christopher Nolan",
                        releaseYear = 2010,
                        synopsis = "Um ladrão especializado em roubar segredos através dos sonhos é contratado para uma missão impossível."
                    ),
                    Movie(
                        title = "O Iluminado",
                        director = "Stanley Kubrick",
                        releaseYear = 1980,
                        synopsis = "Um escritor aceita um emprego como zelador de um hotel isolado durante o inverno, onde eventos sobrenaturais começam a ocorrer."
                    )
                )
                
                defaultMovies.forEach { movie ->
                    repository.insertMovie(movie)
                }
            }
        }
    }
}
