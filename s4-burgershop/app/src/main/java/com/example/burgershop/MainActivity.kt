package com.example.burgershop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.burgershop.ui.theme.BurgerShopTheme

//ACTIVITY PRINCIPAL
// La puerta de entrada de app.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //MaterialTheme: aplica los colores y
            // tipografías por defecto a todo lo que hay dentro
            MaterialTheme{
                // Surface: el "Lienzo" de fondo que ocupa la pantalla
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                  //  CatalogoHamburguesas(catalogoHamburguesas)
                }
            }
        }
    }
}

// MODELO DE DATOS
//EL "molde" que define que informacion tiene cada producto

data class Producto (
    val nombre : String,
    val precio: String,
    val imanResId: Int // el identificador de la imagen en res/drawable
)

// DATOS DE PRUEBA (harcodeados)
// De momento viven aqui mismo, en el código. No vienen de ningún servidor
// ni base de datos

val catalogoHamburguesas = listOf(
    Producto(
        "Clásica con Queso",
        "6,50 €",
        R.drawable.burger_clasica
),

    Producto(
        "BBQ Bacon",
        "7,90 €",
        R.drawable.burger_bbq
    ),

    Producto(
        "Doble Carne",
        "8,50 €",
        R.drawable.burger_doble
    ),
    Producto(
        "Vegetariana",
        "7,20 €",
        R.drawable.burger_vegetariana
    ),
    Producto(
        "Picante Jalapeño",
        "7,80 €",
        R.drawable.burger_picante
    ),
    Producto(
        "Pollo Crispy",
        "6,90 €",
        R.drawable.burger_pollo
    ),

    )
