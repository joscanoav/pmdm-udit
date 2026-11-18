
//
// ARCHIVO PRINCIPAL DE UDITFLIX
//
// Este archivo inicia nuestra aplicación Flutter.
//
// Desde aquí indicamos cuál será la primera pantalla
// que verá el usuario.
//

import 'package:flutter/material.dart';

import 'screens/catalogo_screen.dart';

// Función principal.
// En Dart, main() es el punto de entrada del programa.

void main() {

  // Ejecutamos la aplicación Flutter.
  runApp(const UDITflixApp());

}

// Widget principal de la aplicación.
// MaterialApp proporciona los componentes visuales
// y la configuración general de nuestra aplicación.

class UDITflixApp extends StatelessWidget {

  const UDITflixApp({super.key});

  @override
  Widget build(BuildContext context) {

    return MaterialApp(

      // Quitamos la etiqueta de depuración.
      debugShowCheckedModeBanner: false,

      // Nombre de la aplicación.
      title: 'UDITflix',

      // Configuración visual general.
      theme: ThemeData(

        // Utilizamos Material Design.
        useMaterial3: true,

        // Color principal de la aplicación.
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.deepPurple,
        ),
      ),

      // Primera pantalla que se muestra.
      home: const CatalogoScreen(),

    );
  }
}
