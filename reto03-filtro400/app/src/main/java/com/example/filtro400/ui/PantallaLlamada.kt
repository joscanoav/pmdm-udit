package com.example.filtro400.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.filtro400.ReglaBloqueo
import com.example.filtro400.ia.*
import kotlinx.coroutines.launch

/** Tú haces de LLAMANTE: el filtro decide y, si bloquea, contesta la IA. */
@Composable
fun PantallaLlamada() {
    val scope = rememberCoroutineScope()
    val voz = rememberVoz()
    val chat = remember { mutableStateListOf<Msg>() }
    val lista = rememberLazyListState()
    var numero by remember { mutableStateOf("+34 400 123 456") }
    var enLlamada by remember { mutableStateOf(false) }
    var pensando by remember { mutableStateOf(false) }
    var entrada by remember { mutableStateOf("") }
    var aviso by remember { mutableStateOf<String?>(null) }
    val tarjeta = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)

    LaunchedEffect(chat.size) { if (chat.isNotEmpty()) lista.animateScrollToItem(chat.lastIndex) }

    fun enviar(texto: String) {
        if (texto.isBlank() || pensando || !enLlamada) return
        chat += Msg("user", texto)
        pensando = true
        scope.launch {
            try {
                val r = GroqClient.responder(chat.toList(), BaseConocimiento.PROMPT_LLAMANTE)
                chat += Msg("assistant", r)
                voz.hablar(r)
            } catch (e: Exception) {
                chat += Msg("assistant", "⚠️ Error de IA: ${e.message?.take(150)}")
            }
            pensando = false
        }
    }
    val escuchar = rememberEscuchar(voz) { enviar(it) }

    fun iniciar() {
        if (!ReglaBloqueo.debeBloquearse(numero)) {
            RegistroLlamadas.agregar(LlamadaRegistrada(numero, false, RegistroLlamadas.ahora(), "Pasó al teléfono con normalidad."))
            aviso = "✅ El filtro la deja pasar: sonaría con normalidad. Prueba con un número que empiece por 400."
            return
        }
        aviso = null
        chat.clear()
        chat += Msg("assistant", BaseConocimiento.SALUDO)
        enLlamada = true
        voz.hablar(BaseConocimiento.SALUDO)
    }

    fun colgar() {
        voz.callar()
        enLlamada = false
        val copia = chat.toList()
        val num = numero
        scope.launch {
            val resumen = if (copia.count { it.role == "user" } == 0) "El llamante no dejó mensaje."
            else try { GroqClient.resumir(copia) } catch (e: Exception) { "Mensaje recibido (sin resumen: ${e.message?.take(60)})." }
            RegistroLlamadas.agregar(LlamadaRegistrada(num, true, RegistroLlamadas.ahora(), resumen))
            aviso = "📝 Llamada registrada. Ve a la pestaña Asistente y pregúntale qué llamadas has recibido."
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        CabeceraDegradada("📞 Simulador de llamada", "Haz de llamante y comprueba si la IA contesta")

        if (!enLlamada) {
            Card(colors = tarjeta, shape = RoundedCornerShape(20.dp)) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = numero, onValueChange = { numero = it }, label = { Text("Número que llama") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
                    )
                    Button(onClick = { iniciar() }, modifier = Modifier.fillMaxWidth()) { Text("📲 Simular llamada entrante") }
                }
            }
            aviso?.let {
                Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.fillMaxWidth()) {
                    Text(it, Modifier.padding(16.dp))
                }
            }
        } else {
            Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.errorContainer, modifier = Modifier.fillMaxWidth()) {
                Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("🚫 $numero bloqueada → 🤖 atiende la IA", Modifier.weight(1f))
                    TextButton(onClick = { voz.sonido = !voz.sonido }) { Text(if (voz.sonido) "🔊" else "🔇") }
                }
            }
            LazyColumn(Modifier.weight(1f), state = lista, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(chat) { BurbujaMsg(it) }
                if (pensando) item { Text("🤖 …") }
            }
            BarraEntrada(entrada, { entrada = it }, { enviar(entrada); entrada = "" }, escuchar, voz.escuchando, "Habla como llamante…")
            Button(
                onClick = { colgar() }, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) { Text("📵 Colgar y guardar mensaje") }
        }
    }
}
