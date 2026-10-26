package com.example.filtro400.ia

import androidx.compose.runtime.mutableStateListOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class LlamadaRegistrada(
    val numero: String,
    val bloqueada: Boolean,
    val hora: String,
    val resumen: String
)

/** Registro EN MEMORIA (suficiente para la demo; en la Fase 2/3 pasará a SQLite). */
object RegistroLlamadas {
    val llamadas = mutableStateListOf<LlamadaRegistrada>()

    fun ahora(): String = SimpleDateFormat("HH:mm", Locale("es", "ES")).format(Date())

    fun agregar(l: LlamadaRegistrada) { llamadas.add(0, l) }

    fun textoParaIA(): String =
        if (llamadas.isEmpty()) "No hay llamadas registradas."
        else llamadas.mapIndexed { i, l ->
            "${i + 1}. ${l.hora} · ${l.numero} · " +
                (if (l.bloqueada) "BLOQUEADA y atendida por el asistente" else "permitida") +
                " · ${l.resumen}"
        }.joinToString("\n")
}
