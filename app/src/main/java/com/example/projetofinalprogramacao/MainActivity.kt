package com.example.projetofinalprogramacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projetofinalprogramacao.ui.theme.ProjetoFinalProgramacaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetoFinalProgramacaoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        EcraPrincipal()
                    }
                }
            }
        }
    }
}

@Composable
fun EcraPrincipal() {
    var ecraAtual by remember { mutableStateOf("menu") }
    var tipoSelecionado by remember { mutableStateOf("") }
    var generoSelecionado by remember { mutableStateOf<CategoriaGenero?>(null) }
    var itemSelecionado by remember { mutableStateOf<ItemMedia?>(null) }

    when (ecraAtual) {
        "menu" -> MenuPrincipalFilmesSeries(
            onCategoriaSelecionada = { tipo ->
                tipoSelecionado = tipo
                ecraAtual = "generos"
            }
        )
        "generos" -> {
            EcraGeneros(
                tipo = tipoSelecionado,
                onGeneroSelecionado = { genero ->
                    generoSelecionado = genero
                    ecraAtual = "lista"
                },
                onVoltar = { ecraAtual = "menu" }
            )
        }
        "lista" -> {
            EcraListaItens(
                genero = generoSelecionado,
                onItemSelecionado = { item ->
                    itemSelecionado = item
                    ecraAtual = "detalhes"
                },
                onVoltar = { ecraAtual = "generos" }
            )
        }
        "detalhes" -> {
            EcraDetalheFilme(
                item = itemSelecionado,
                onVerComentarios = { ecraAtual = "comentarios" },
                onVoltar = { ecraAtual = "lista" }
            )
        }
        "comentarios" -> {
            EcraComentarios(
                item = itemSelecionado,
                onVoltar = { ecraAtual = "detalhes" }
            )
        }
    }
}

@Composable
fun MenuPrincipalFilmesSeries(onCategoriaSelecionada: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "O que está curioso de procurar hoje?",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = { onCategoriaSelecionada("Filmes") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text(text = "Filmes", style = MaterialTheme.typography.bodyLarge)
        }

        Button(
            onClick = { onCategoriaSelecionada("Séries") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Séries", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun EcraGeneros(tipo: String, onGeneroSelecionado: (CategoriaGenero) -> Unit, onVoltar: () -> Unit) {
    val listaGeneros = if (tipo == "Filmes") FonteDeDados.generosFilmes else FonteDeDados.generosSeries

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Géneros de $tipo",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp, bottom = 32.dp)
        )

        listaGeneros.forEach { genero ->
            Button(
                onClick = { onGeneroSelecionado(genero) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text(text = genero.nomeGenero, style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onVoltar, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Voltar ao Menu")
        }
    }
}

@Composable
fun EcraListaItens(genero: CategoriaGenero?, onItemSelecionado: (ItemMedia) -> Unit, onVoltar: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = genero?.nomeGenero ?: "Lista",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )

        genero?.itens?.forEach { item ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                onClick = { onItemSelecionado(item) }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.imagemRes),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp).padding(end = 16.dp)
                    )
                    Text(text = item.titulo, style = MaterialTheme.typography.titleLarge)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onVoltar, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Voltar aos Géneros")
        }
    }
}

@Composable
fun EcraDetalheFilme(item: ItemMedia?, onVerComentarios: () -> Unit, onVoltar: () -> Unit) {
    if (item == null) return

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.size(width = 280.dp, height = 360.dp).shadow(8.dp),
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White),
            shape = MaterialTheme.shapes.medium
        ) {
            Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = item.imagemRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = item.titulo, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 8.dp))
                Text(text = item.sinopse, style = MaterialTheme.typography.bodyMedium)
            }
        }

        Button(
            onClick = onVerComentarios,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(text = "Ver Comentários")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onVoltar, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
            Text(text = "Voltar à Lista")
        }
    }
}
@Composable
fun EcraComentarios(item: ItemMedia?, onVoltar: () -> Unit) {
    if (item == null) return

    // Estados para controlar o pop-up de escrita
    var mostrarDialogo by remember { mutableStateOf(false) }
    var textoNovoComentario by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Barra Superior: Título à esquerda, Botão "+" à direita
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Opiniões: ${item.titulo}",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f) // Dá espaço para o botão não fugir
            )

            // Botão do "+" usando um Button pequeno (ou podes usar IconButton se preferires)
            Button(
                onClick = { mostrarDialogo = true },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "+", style = MaterialTheme.typography.titleLarge)
            }
        }

        // Lista dinâmica de comentários (Lê diretamente do teu objeto ItemMedia)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(item.comentarios) { comentario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Nome de quem comentou (podes deixar fixo "Utilizador" para já)
                        Text(
                            text = comentario.autor,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = comentario.texto,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        Button(
            onClick = onVoltar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Voltar ao Filme")
        }
    }

    // CAIXA DE DIÁLOGO (Pop-up) para escrever o novo comentário
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text(text = "Adicionar Comentário") },
            text = {
                Column {
                    Text(text = "O que achaste do conteúdo?", modifier = Modifier.padding(bottom = 8.dp))
                    TextField(
                        value = textoNovoComentario,
                        onValueChange = { textoNovoComentario = it },
                        placeholder = { Text("Escreve aqui...") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (textoNovoComentario.isNotBlank()) {
                            // Adiciona o comentário diretamente à lista mutável do filme!
                            item.comentarios.add(
                                Comentario(autor = "Utilizador", texto = textoNovoComentario)
                            )
                            // Limpa o texto e fecha o pop-up
                            textoNovoComentario = ""
                            mostrarDialogo = false
                        }
                    }
                ) {
                    Text("Submeter")
                }
            },
            dismissButton = {
                Button(onClick = { mostrarDialogo = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

