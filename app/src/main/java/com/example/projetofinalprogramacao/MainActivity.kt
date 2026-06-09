package com.example.projetofinalprogramacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    MenuPrincipalFilmesSeries {  }
}

@Composable
fun MenuPrincipalFilmesSeries(onCategoriaSelecionada: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "O que está curioso de procurar hoje?",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Botão para Filmes
        Button(
            onClick = { onCategoriaSelecionada("Filmes") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Filmes", style = MaterialTheme.typography.bodyLarge)
        }

        // Botão para Séries
        Button(
            onClick = { onCategoriaSelecionada("Séries") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Séries", style = MaterialTheme.typography.bodyLarge)
        }
    }
}