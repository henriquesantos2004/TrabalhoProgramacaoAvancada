package com.example.projetofinalprogramacao

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

// 1. Criar a classe que representa um comentário individual
data class Comentario(
    val autor: String,
    val texto: String
)

// 2. Atualizar o ItemMedia para receber a lista mutável de comentários
data class ItemMedia(
    val id: Int,
    val titulo: String,
    val sinopse: String,
    val imagemRes: Int,
    // Criamos uma lista mutável que começa vazia por padrão
    val comentarios: SnapshotStateList<Comentario> = mutableStateListOf()
)

data class CategoriaGenero(
    val nomeGenero: String,
    val itens: List<ItemMedia>
)

object FonteDeDados {
    // Filmes de Comédia (Usamos o .apply para preencher comentários iniciais de teste)
    val filmesComedia = listOf(
        ItemMedia(1, "Shrek", "Um ogre verde vê a sua solidão invadida por personagens de contos de fadas.", R.drawable.shrek).apply {
            comentarios.addAll(
                listOf(
                    Comentario("Anónimo", "Este filme marcou a minha infância! Muito bom."),
                    Comentario("Maria", "Amei as piadas do Burro, rio-me sempre.")
                )
            )
        },
        ItemMedia(2, "A Máscara", "Um funcionário bancário tímido descobre uma máscara mágica que o transforma num herói louco.", R.drawable.themask).apply {
            comentarios.add(Comentario("Carlos", "O Jim Carrey está incrível neste papel!"))
        }
    )

    // Filmes de Terror
    val filmesTerror = listOf(
        ItemMedia(3, "O Telefone Negro", "Um rapaz de 13 anos é raptado e começa a receber chamadas de um telefone desligado.", R.drawable.otelefonenegroo)
    )

    val generosFilmes = listOf(
        CategoriaGenero("Comédia", filmesComedia),
        CategoriaGenero("Terror", filmesTerror)
    )

    // Séries de Comédia
    val seriesComedia = listOf(
        ItemMedia(4, "The Office", "O quotidiano cómico e absurdo dos funcionários de uma empresa de papel.", R.drawable.theofficee).apply {
            comentarios.add(Comentario("Tiago", "A melhor série de comédia de sempre, sem dúvida."))
        }
    )

    val generosSeries = listOf(
        CategoriaGenero("Comédia", seriesComedia)
    )
}