
//
// ARCHIVO PRINCIPAL DE UDITFLIX
//
// Este archivo inicia nuestra aplicación Flutter.
// Desde aquí indicamos cuál será la primera pantalla
// que verá el usuario y el tema visual de la aplicación.
//

import 'package:flutter/material.dart';

import 'screens/catalogo_screen.dart';

// Función principal. En Dart, main() es el punto de entrada.

void main() {
  runApp(const UDITflixApp());
}

// Widget principal de la aplicación.
class UDITflixApp extends StatelessWidget {

  const UDITflixApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(

      debugShowCheckedModeBanner: false,
      title: 'UDITflix',

      // Tema oscuro inspirado en las aplicaciones de streaming.
      // El rojo es el color de acento y el morado se mantiene
      // como guiño a la identidad anterior de UDITflix.
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFFE50914),
          brightness: Brightness.dark,
        ).copyWith(secondary: Colors.deepPurpleAccent),
        scaffoldBackgroundColor: const Color(0xFF0B0B10),
        appBarTheme: const AppBarTheme(
          backgroundColor: Color(0xFF0B0B10),
          foregroundColor: Colors.white,
          elevation: 0,
        ),
      ),

      // Primera pantalla que se muestra.
      home: const CatalogoScreen(),
    );
  }
}
