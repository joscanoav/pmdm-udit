package com.example.filtro400

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.filtro400.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemaFiltro {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) { Raiz() }
            }
        }
    }
}

@Composable
fun Raiz() {
    var tab by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("🛡️" to "Filtro", "📞" to "Llamada", "🤖" to "Asistente")
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                tabs.forEachIndexed { i, (emoji, nombre) ->
                    NavigationBarItem(
                        selected = tab == i, onClick = { tab = i },
                        icon = { Text(emoji, fontSize = 22.sp) }, label = { Text(nombre) }
                    )
                }
            }
        }
    ) { pad ->
        Box(Modifier.padding(pad).fillMaxSize()) {
            when (tab) {
                0 -> PantallaFiltro()
                1 -> PantallaLlamada()
                else -> PantallaAsistente()
            }
        }
    }
}
