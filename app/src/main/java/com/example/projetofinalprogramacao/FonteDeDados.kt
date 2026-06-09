package com.example.projetofinalprogramacao

data class ItemMedia(
    val id: Int,
    val titulo: String,
    val sinopse: String,
    val imagemRes: Int
)

data class CategoriaGenero(
    val nomeGenero: String,
    val itens: List<ItemMedia>
)

object FonteDeDados {
    // Filmes de Comédia
    val filmesComedia = listOf(
        ItemMedia(1, "Shrek", "Um ogre verde vê a sua solidão invadida por personagens de contos de fadas.", android.R.drawable.ic_menu_gallery),
        ItemMedia(2, "A Máscara", "Um funcionário bancário tímido descobre uma máscara mágica que o transforma num herói louco.", android.R.drawable.ic_menu_gallery)
    )

    // Filmes de Terror
    val filmesTerror = listOf(
        ItemMedia(3, "O Telefone Negro", "Um rapaz de 13 anos é raptado e começa a receber chamadas de um telefone desligado.", android.R.drawable.ic_menu_gallery)
    )

    val generosFilmes = listOf(
        CategoriaGenero("Comédia", filmesComedia),
        CategoriaGenero("Terror", filmesTerror)
    )

    // Séries de Comédia
    val seriesComedia = listOf(
        ItemMedia(4, "The Office", "O quotidiano cómico e absurdo dos funcionários de uma empresa de papel.", android.R.drawable.ic_menu_gallery)
    )

    val generosSeries = listOf(
        CategoriaGenero("Comédia", seriesComedia)
    )
}