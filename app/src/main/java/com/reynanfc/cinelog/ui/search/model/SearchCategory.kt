package com.reynanfc.cinelog.ui.search.model

/**
 * Categorias exibidas nos chips da busca.
 * [genreId] é o id do gênero no TMDB; null significa "sem filtro".
 */
enum class SearchCategory(val label: String, val genreId: Int?) {
    ALL(label = "Todos", genreId = null),
    DRAMA(label = "Drama", genreId = 18),
    COMEDY(label = "Comédia", genreId = 35),
    SCIENCE_FICTION(label = "Ficção científica", genreId = 878),
    HORROR(label = "Terror", genreId = 27)
}
