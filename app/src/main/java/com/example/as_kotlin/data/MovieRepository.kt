package com.example.as_kotlin.data

import com.example.as_kotlin.data.model.Movie

object MovieRepository {
    private val movieList = listOf(
        Movie(
            id = 1,
            title = "O Poderoso Chefão",
            director = "Francis Ford Coppola",
            releaseYear = 1972,
            synopsis = "A saga da família Corleone, uma das mais poderosas famílias da máfia italiana em Nova York."
        ),
        Movie(
            id = 2,
            title = "Pulp Fiction",
            director = "Quentin Tarantino",
            releaseYear = 1994,
            synopsis = "Histórias entrelaçadas de criminosos, boxeadores e outros personagens em Los Angeles."
        ),
        Movie(
            id = 3,
            title = "A Lista de Schindler",
            director = "Steven Spielberg",
            releaseYear = 1993,
            synopsis = "A história real de Oskar Schindler, um empresário alemão que salvou mais de mil judeus durante o Holocausto."
        ),
        Movie(
            id = 4,
            title = "O Senhor dos Anéis: A Sociedade do Anel",
            director = "Peter Jackson",
            releaseYear = 2001,
            synopsis = "Um hobbit embarca em uma jornada épica para destruir um anel mágico e salvar a Terra Média."
        ),
        Movie(
            id = 5,
            title = "Forrest Gump",
            director = "Robert Zemeckis",
            releaseYear = 1994,
            synopsis = "A vida extraordinária de Forrest Gump, um homem simples que vive momentos históricos dos Estados Unidos."
        ),
        Movie(
            id = 6,
            title = "Matrix",
            director = "Lana Wachowski, Lilly Wachowski",
            releaseYear = 1999,
            synopsis = "Um programador descobre que o mundo em que vive é uma simulação computacional."
        ),
        Movie(
            id = 7,
            title = "Clube da Luta",
            director = "David Fincher",
            releaseYear = 1999,
            synopsis = "Um funcionário de escritório insone forma um clube de luta clandestino como forma de terapia."
        ),
        Movie(
            id = 8,
            title = "Interestelar",
            director = "Christopher Nolan",
            releaseYear = 2014,
            synopsis = "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço em busca de um novo lar para a humanidade."
        ),
        Movie(
            id = 9,
            title = "A Origem",
            director = "Christopher Nolan",
            releaseYear = 2010,
            synopsis = "Um ladrão especializado em roubar segredos através dos sonhos é contratado para uma missão impossível."
        ),
        Movie(
            id = 10,
            title = "O Iluminado",
            director = "Stanley Kubrick",
            releaseYear = 1980,
            synopsis = "Um escritor aceita um emprego como zelador de um hotel isolado durante o inverno, onde eventos sobrenaturais começam a ocorrer."
        )
    )

    fun getMovies(): List<Movie> = movieList
}

