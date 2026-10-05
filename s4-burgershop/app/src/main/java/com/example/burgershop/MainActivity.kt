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

