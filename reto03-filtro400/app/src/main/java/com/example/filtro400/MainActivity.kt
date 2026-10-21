package com.example.filtro400

import android.app.role.RoleManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PantallaFiltro()
                }
            }
        }
    }
}

@Composable
fun PantallaFiltro() {
    val context = LocalContext.current

    // RoleManager: el servicio de Android que gestiona "roles" como
    // "app de filtrado de llamadas". Sin el rol, nuestro servicio no se activa.
    val roleManager = remember { context.getSystemService(RoleManager::class.java) }

    // Estado que la pantalla "vigila": si el filtro está activo.
    var filtroActivo by remember {
        mutableStateOf(roleManager.isRoleHeld(RoleManager.ROLE_CALL_SCREENING))
    }
    var numeroPrueba by remember { mutableStateOf("") }
    var resultadoPrueba by remember { mutableStateOf("") }

    // Cuando el usuario responde al diálogo del sistema, volvemos aquí
    // y volvemos a comprobar si ahora tenemos el rol.
    val lanzador = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        filtroActivo = roleManager.isRoleHeld(RoleManager.ROLE_CALL_SCREENING)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Filtro 400", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (filtroActivo) "✅ Filtro ACTIVO" else "⚠️ Filtro inactivo",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Pide al sistema el rol de filtrado. Android muestra su propio
        // diálogo: la app no puede darse el rol a sí misma.
        Button(
            onClick = {
                val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
                lanzador.launch(intent)
            },
            enabled = !filtroActivo
        ) {
            Text("Activar filtro de llamadas")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Probador: comprueba la regla sin necesidad de recibir una llamada.
        Text("Probador de números", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = numeroPrueba,
            onValueChange = { numeroPrueba = it },
            label = { Text("Ej. +34 400 123 456") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            resultadoPrueba = if (ReglaBloqueo.debeBloquearse(numeroPrueba))
                "🚫 Se BLOQUEARÍA" else "✅ Pasaría"
        }) {
            Text("Probar")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(resultadoPrueba, fontSize = 20.sp)
    }
}