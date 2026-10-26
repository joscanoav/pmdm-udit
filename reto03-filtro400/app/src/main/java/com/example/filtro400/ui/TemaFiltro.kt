package com.example.filtro400.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Esquema = darkColorScheme(
    primary = Color(0xFF8AA4FF), onPrimary = Color(0xFF0B1020),
    primaryContainer = Color(0xFF2B3A78), onPrimaryContainer = Color(0xFFE8ECFF),
    secondary = Color(0xFF5EEAD4), onSecondary = Color(0xFF062A25),
    background = Color(0xFF0B1020), onBackground = Color(0xFFE8ECFF),
    surface = Color(0xFF131A33), onSurface = Color(0xFFE8ECFF),
    surfaceVariant = Color(0xFF1C2545), onSurfaceVariant = Color(0xFFB8C0E0),
    error = Color(0xFFFF6B7A), errorContainer = Color(0xFF4A1E28), onErrorContainer = Color(0xFFFFD9DE)
)

object Paleta {
    val Degradado = listOf(Color(0xFF3A4FD7), Color(0xFF7C3AED))
}

@Composable
fun TemaFiltro(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Esquema, content = content)
}
