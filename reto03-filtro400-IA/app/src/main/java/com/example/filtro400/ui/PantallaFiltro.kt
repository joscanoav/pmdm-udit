package com.example.filtro400.ui

import android.app.role.RoleManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filtro400.ReglaBloqueo
import com.example.filtro400.ia.RegistroLlamadas

@Composable
fun PantallaFiltro() {
    val context = LocalContext.current
    val roleManager = remember { context.getSystemService(RoleManager::class.java) }
    var filtroActivo by remember { mutableStateOf(roleManager.isRoleHeld(RoleManager.ROLE_CALL_SCREENING)) }
    val lanzador = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        filtroActivo = roleManager.isRoleHeld(RoleManager.ROLE_CALL_SCREENING)
    }

    var numero by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Boolean?>(null) }
    val bloqueadas = RegistroLlamadas.llamadas.count { it.bloqueada }
    val permitidas = RegistroLlamadas.llamadas.size - bloqueadas
    val tarjeta = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CabeceraDegradada("🛡️ Filtro 400", "Bloquea las llamadas comerciales del prefijo 400")

        Card(colors = tarjeta, shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    if (filtroActivo) "✅ Filtro ACTIVO" else "⚠️ Filtro inactivo",
                    fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    color = if (filtroActivo) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                )
                Text(
                    if (filtroActivo) "Las llamadas del 400 se rechazan antes de sonar."
                    else "Android pedirá permiso para que esta app filtre tus llamadas.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Button(
                    onClick = { lanzador.launch(roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)) },
                    enabled = !filtroActivo, modifier = Modifier.fillMaxWidth()
                ) { Text("Activar filtro de llamadas") }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Estadistica(bloqueadas, "🚫 Bloqueadas", Modifier.weight(1f))
            Estadistica(permitidas, "✅ Permitidas", Modifier.weight(1f))
        }

        Card(colors = tarjeta, shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("🧪 Probador de números", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                OutlinedTextField(
                    value = numero, onValueChange = { numero = it; resultado = null },
                    label = { Text("Ej. +34 400 123 456") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
                )
                Button(onClick = { resultado = ReglaBloqueo.debeBloquearse(numero) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Probar")
                }
                resultado?.let { bloquea ->
                    Surface(
                        shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth(),
                        color = if (bloquea) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            if (bloquea) "🚫 Se BLOQUEARÍA" else "✅ Pasaría",
                            Modifier.padding(14.dp), fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Estadistica(valor: Int, etiqueta: String, modifier: Modifier) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), shape = RoundedCornerShape(20.dp)) {
        Column(Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$valor", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(etiqueta, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
