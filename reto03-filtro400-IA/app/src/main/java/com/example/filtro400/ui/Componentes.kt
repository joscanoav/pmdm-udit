package com.example.filtro400.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.filtro400.ia.Msg

@Composable
fun CabeceraDegradada(titulo: String, subtitulo: String) {
    Box(
        Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Brush.horizontalGradient(Paleta.Degradado))
            .padding(20.dp)
    ) {
        Column {
            Text(titulo, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(Modifier.height(4.dp))
            Text(subtitulo, color = Color.White.copy(alpha = 0.85f))
        }
    }
}

@Composable
fun BurbujaMsg(m: Msg) {
    val yo = m.role == "user"
    Row(Modifier.fillMaxWidth(), horizontalArrangement = if (yo) Arrangement.End else Arrangement.Start) {
        Surface(
            color = if (yo) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(
                topStart = 18.dp, topEnd = 18.dp,
                bottomStart = if (yo) 18.dp else 4.dp, bottomEnd = if (yo) 4.dp else 18.dp
            ),
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            Text(m.text, Modifier.padding(horizontal = 14.dp, vertical = 10.dp))
        }
    }
}

@Composable
fun BarraEntrada(
    texto: String, onTexto: (String) -> Unit, onEnviar: () -> Unit,
    onMic: () -> Unit, escuchando: Boolean, placeholder: String
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = texto, onValueChange = onTexto, modifier = Modifier.weight(1f),
            placeholder = { Text(placeholder) }, singleLine = true, shape = RoundedCornerShape(24.dp)
        )
        FilledIconButton(onClick = onMic) { Text(if (escuchando) "…" else "🎤") }
        FilledIconButton(onClick = onEnviar) { Text("➤") }
    }
}
