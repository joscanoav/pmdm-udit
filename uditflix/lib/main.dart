import 'package:flutter/material.dart';
import 'screens/catalogo_screen.dart';

// Punto de entrada de aplicacion

void main() {
  runApp(const MyApp());
}

// Widget principal de UDITflix

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      //Nombre de nuestra aplicacion
      title: 'UDITflix',
      // Ocultamos la etiqueta DEBUG
      debugShowCheckedModeBanner: false,
      // Configuracion visual general.
      theme: ThemeData(
        useMaterial3: true,
        // Color principal de nuestra aplicacion
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFF5B2D90),
        ),
        // Color de fondo de pantallas
        scaffoldBackgroundColor: const Color(0xFFF7F5FA),

        // Aspecto de la barra superior
        appBarTheme: const AppBarTheme(
          backgroundColor: Color(0xFF5B2D90),
          foregroundColor: Colors.white,
          centerTitle: false,
        ),
      ),

      // Primera pantalla que verá el usuario.
      home: const CatalogoScreen(),

    );
  }
}