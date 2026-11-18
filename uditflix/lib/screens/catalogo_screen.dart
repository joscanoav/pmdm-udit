
//
// PANTALLA DEL CATÁLOGO
//
// Esta pantalla muestra todos los contenidos disponibles.
//
// Utilizamos:
// - Scaffold: estructura general de una pantalla.
// - AppBar: barra superior.
// - ListView.builder: lista de elementos.
// - Card: tarjeta visual.
// - ListTile: organización del contenido de cada tarjeta.
//

import 'package:flutter/material.dart';

import '../data/contenidos.dart';
import '../models/contenido.dart';
import 'ficha_screen.dart';

// StatelessWidget representa una pantalla cuya interfaz
// no necesita mantener un estado cambiante propio.

class CatalogoScreen extends StatelessWidget {

  const CatalogoScreen({super.key});

  // Elegimos un icono dependiendo del tipo de contenido.
  // Así diferenciamos visualmente vídeos, cómics y juegos.

  IconData obtenerIcono(String tipo) {

    if (tipo == 'Vídeo') {
      return Icons.play_circle;
    }

    if (tipo == 'Cómic') {
      return Icons.menu_book;
    }

    if (tipo == 'Juego') {
      return Icons.sports_esports;
    }

    return Icons.movie;
  }

  @override
  Widget build(BuildContext context) {

    // Scaffold proporciona la estructura de la pantalla.
    return Scaffold(

      // Barra superior de la aplicación.
      appBar: AppBar(
        title: const Text('UDITflix'),
        backgroundColor: Colors.deepPurple,
        foregroundColor: Colors.white,
      ),

      // Cuerpo principal de la pantalla.
      body: ListView.builder(

        // Número de elementos que tendrá nuestra lista.
        itemCount: contenidos.length,

        // Flutter ejecuta esta función para construir
        // cada elemento de la lista.
        itemBuilder: (context, index) {

          // Recuperamos el contenido correspondiente.
          final Contenido contenido = contenidos[index];

          // Construimos una tarjeta para cada contenido.
          return Card(

            margin: const EdgeInsets.symmetric(
              horizontal: 12,
              vertical: 6,
            ),

            child: ListTile(

              // Icono según el tipo de contenido.
              leading: CircleAvatar(
                child: Icon(obtenerIcono(contenido.tipo)),
              ),

              // Título del contenido.
              title: Text(
                contenido.titulo,
                style: const TextStyle(
                  fontWeight: FontWeight.bold,
                ),
              ),

              // Tipo y duración.
              subtitle: Text(
                '${contenido.tipo} · ${contenido.duracion}',
              ),

              // Flecha para indicar que podemos entrar.
              trailing: const Icon(Icons.arrow_forward_ios),

              // Cuando el usuario pulsa una tarjeta,
              // navegamos hacia la ficha del contenido.
              onTap: () {

                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => FichaScreen(
                      contenido: contenido,
                    ),
                  ),
                );

              },
            ),
          );
        },
      ),
    );
  }
}
