package com.example.filtro400.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.filtro400.ia.*
import kotlinx.coroutines.launch

/** Tú eres el DUEÑO: hablas con la IA y ella te cuenta qué llamadas se han recibido. */
@Composable
fun PantallaAsistente() {
    val scope = rememberCoroutineScope()
    val voz = rememberVoz()
    val chat = remember { mutableStateListOf<Msg>() }
    val lista = rememberLazyListState()
    var entrada by remember { mutableStateOf("") }
    var pensando by remember { mutableStateOf(false) }

    LaunchedEffect(chat.size) { if (chat.isNotEmpty()) lista.animateScrollToItem(chat.lastIndex) }

    fun enviar(texto: String) {
        if (texto.isBlank() || pensando) return
        chat += Msg("user", texto)
        pensando = true
        scope.launch {
            try {
                // El registro se lee en CADA mensaje, así la IA siempre ve las llamadas más recientes.
                val r = GroqClient.responder(chat.toList(), BaseConocimiento.promptDueno(RegistroLlamadas.textoParaIA()))
                chat += Msg("assistant", r)
                voz.hablar(r)
            } catch (e: Exception) {
                chat += Msg("assistant", "⚠️ Error de IA: ${e.message?.take(150)}")
            }
            pensando = false
        }
    }
    val escuchar = rememberEscuchar(voz) { enviar(it) }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        CabeceraDegradada("🤖 Tu asistente", "Pregúntale qué llamadas has recibido")
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Llamadas en registro: ${RegistroLlamadas.llamadas.size}", Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            TextButton(onClick = { voz.sonido = !voz.sonido }) { Text(if (voz.sonido) "🔊" else "🔇") }
        }
        LazyColumn(Modifier.weight(1f), state = lista, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (chat.isEmpty()) item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Prueba con:", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    FilledTonalButton(onClick = { enviar("Hola, ¿qué tal?") }) { Text("👋 Hola, ¿qué tal?") }
                    FilledTonalButton(onClick = { enviar("¿Qué llamadas he recibido?") }) { Text("📋 ¿Qué llamadas he recibido?") }
                    FilledTonalButton(onClick = { enviar("¿Qué es este proyecto?") }) { Text("💡 ¿Qué es este proyecto?") }
                }
            }
            items(chat) { BurbujaMsg(it) }
            if (pensando) item { Text("🤖 …") }
        }
        BarraEntrada(entrada, { entrada = it }, { enviar(entrada); entrada = "" }, escuchar, voz.escuchando, "Escribe o habla…")
    }
}
