package com.example.reto1_tarjetapresentacion

// ---------------------------------------------------------------------
// IMPORTS
// Cada import trae una pieza concreta que usamos más abajo.
// En clase: se generan solos con Alt+Enter sobre la palabra en rojo.
// ---------------------------------------------------------------------

// Android "puro" (no es de Compose): para abrir el navegador desde el botón
import android.content.Intent          // el "mensajero" que le pide a Android que haga algo (aquí: abrir una URL)
import android.net.Uri                 // convierte el texto de una URL en un formato que Android entiende
import android.os.Bundle               // estado de la pantalla al crearse (lo exige toda Activity)

// Esqueleto de la pantalla (siempre igual en cualquier app Android)
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Layout: cómo se organizan los elementos en la pantalla
import androidx.compose.foundation.layout.Arrangement     // reparto vertical/horizontal dentro de un Column/Row
import androidx.compose.foundation.layout.Column          // apila elementos en vertical (como un flexbox column)
import androidx.compose.foundation.layout.Spacer          // hueco vacío entre elementos
import androidx.compose.foundation.layout.fillMaxSize     // ocupa TODO el ancho y alto disponibles
import androidx.compose.foundation.layout.fillMaxWidth    // ocupa un % del ancho disponible
import androidx.compose.foundation.layout.height          // fija una altura (se usa en el Spacer)
import androidx.compose.foundation.layout.padding         // margen interior
import androidx.compose.foundation.layout.size            // fija ancho y alto iguales (para la foto)

// Imagen y su recorte circular
import androidx.compose.foundation.Image                  // muestra una imagen en pantalla
import androidx.compose.foundation.shape.CircleShape      // la forma "círculo" que usamos para recortar la foto
import androidx.compose.ui.draw.clip                      // recorta un elemento con la forma que le pases
import androidx.compose.ui.layout.ContentScale            // cómo se ajusta la imagen dentro de su marco (aquí: Crop)
import androidx.compose.ui.res.painterResource            // carga una imagen desde res/drawable

// Componentes visuales (Material Design 3)
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme           // colores/tipografías del tema de la app
import androidx.compose.material3.Surface                 // fondo base de toda la pantalla
import androidx.compose.material3.Text

// Otros
import androidx.compose.runtime.Composable                 // marca una función como "dibujable" en pantalla
import androidx.compose.ui.Alignment                        // alineación horizontal/vertical
import androidx.compose.ui.Modifier                          // "decorador" que se encadena (tamaño, margen, forma...)
import androidx.compose.ui.platform.LocalContext             // así un Composable accede al "contexto" de Android
import androidx.compose.ui.text.font.FontWeight               // grosor del texto (ej. negrita)
import androidx.compose.ui.tooling.preview.Preview             // permite ver la UI sin abrir el emulador
import androidx.compose.ui.unit.dp                              // unidad de medida para tamaños/márgenes
import androidx.compose.ui.unit.sp                              // unidad de medida para tamaño de texto
import com.example.reto1_tarjetapresentacion.ui.theme.Reto1TarjetaPresentacionTheme  // el tema visual del proyecto


// ---------------------------------------------------------------------
// MainActivity: la "puerta de entrada" de la app.
// Todo Android la necesita; aquí casi nunca se toca nada más
// que la línea setContent { ... }.
// ---------------------------------------------------------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicamos el tema de colores del proyecto a todo lo de dentro
            Reto1TarjetaPresentacionTheme {
                // Surface = el "lienzo" de fondo que ocupa toda la pantalla
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Aquí llamamos a NUESTRA función, la que dibuja la tarjeta
                    TarjetaPresentacion()
                }
            }
        }
    }
}


// ---------------------------------------------------------------------
// PÍLDORA TÉCNICA: la tarjeta de presentación
// @Composable = "esta función dibuja algo en pantalla"
// ---------------------------------------------------------------------
@Composable
fun TarjetaPresentacion() {
    // LocalContext: así un Composable "pide prestado" el contexto de Android
    // Lo necesitamos para poder abrir el navegador desde el botón.
    val context = LocalContext.current
    // 1. COLUMN: apila los elementos de arriba a abajo (como un flexbox vertical)
    Column(
        modifier = Modifier
            .fillMaxSize()          // ocupa toda la pantalla
            .padding(16.dp),        // margen para que nada toque los bordes
        horizontalAlignment = Alignment.CenterHorizontally,  // centra en el eje X
        verticalArrangement = Arrangement.Center             // centra en el eje Y
    ) {
        // 2. IMAGE: la foto de perfil
        // Requiere un archivo 'foto_perfil' dentro de res/drawable
        Image(
            painter = painterResource(id = R.drawable.foto_perfil),
            contentDescription = "Foto de perfil de usuario",  // para accesibilidad (lectores de pantalla)
            modifier = Modifier
                .size(150.dp)           // tamaño fijo: 150x150
                .clip(CircleShape),     // la recorta en forma de círculo
            contentScale = ContentScale.Crop  // rellena el círculo sin deformar la imagen
        )

        // Hueco vacío entre la imagen y el texto
        Spacer(modifier = Modifier.height(24.dp))

        // 3. TEXT: nombre
        Text(
            text = "Jorge Oscanoa",       // cada alumno pone el suyo
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        // TEXT: rol o profesión
        Text(
            text = "Desarrollador MERN & Docente DAM",   // cada alumno pone el suyo
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary   // color secundario del tema
        )

        // Hueco más grande antes del botón
        Spacer(modifier = Modifier.height(32.dp))

        // 4. BUTTON: enlace a GitHub
        Button(
            onClick = {
                // 1. Intent ACTION_VIEW: le decimos a Android "quiero VER este recurso"
                //    y el sistema decide qué app usar (normalmente, el navegador)
                // 2. Uri.parse convierte el texto de la URL en el formato que Android entiende
                // 3. startActivity lanza esa acción
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/joscanoav"))
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth(0.8f)   // ocupa el 80% del ancho de pantalla
        ) {
            Text(text = "Mi Perfil de GitHub")
        }
    }
}


// ---------------------------------------------------------------------
// PREVIEW: ver los cambios sin abrir el emulador (panel derecho de Android Studio)
// ---------------------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun TarjetaPreview() {
    Reto1TarjetaPresentacionTheme {
        TarjetaPresentacion()
    }
}