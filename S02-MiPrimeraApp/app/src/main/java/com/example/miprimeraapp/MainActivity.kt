package com.example.miprimeraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.miprimeraapp.ui.theme.MiPrimeraAppTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        // setContent es el puente: conecta la pantalla en blanco con nuestro diseño
//        setContent {
//            MiPrimeraAppTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    // Llamamos a nuestro componente visual y le pasamos información
//                    Greeting(
//                        name = "Mundo Jorge",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//// @Composable avisa a Android Studio que esta función sirve para dibujar en la pantalla
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!", // El símbolo $ inyecta el valor de la variable en el texto
//        modifier = modifier
//    )
//}
//
//// @Preview dibuja una ventana lateral para ver los cambios sin tener que usar el emulador pesado
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MiPrimeraAppTheme {
//        Greeting("Mundo Android")
//    }
//}
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {

    val nombre = "Jorge"
    val curso = "2º DAM"

    Text(
        text = "Hola, soy $nombre\nCurso: $curso"
    )
}
