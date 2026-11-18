
//
// PANTALLA DEL REPRODUCTOR
//
// Esta es una primera versión visual del reproductor.
//
// Todavía no reproduce vídeos reales.
// Más adelante integraremos un reproductor multimedia.
//

import 'package:flutter/material.dart';

import '../models/contenido.dart';

class ReproductorScreen extends StatelessWidget {

  // Contenido que queremos visualizar.
  final Contenido contenido;

  const ReproductorScreen({
    super.key,
    required this.contenido,
  });

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      appBar: AppBar(
        title: const Text('Reproductor UDITflix'),
      ),

      body: Center(

        child: Column(

          mainAxisAlignment: MainAxisAlignment.center,

          children: [

            // Icono provisional de reproducción.
            const Icon(
              Icons.play_circle_fill,
              size: 110,
              color: Colors.deepPurple,
            ),

            const SizedBox(height: 20),

            // Título del contenido seleccionado.
            Text(
              contenido.titulo,
              textAlign: TextAlign.center,
              style: const TextStyle(
                fontSize: 22,
                fontWeight: FontWeight.bold,
              ),
            ),

            const SizedBox(height: 10),

            // Mensaje que indica que es una versión provisional.
            const Text(
              'Reproductor provisional',
              style: TextStyle(
                fontSize: 16,
                color: Colors.grey,
              ),
            ),

            const SizedBox(height: 20),

            const Text(
              'En una próxima versión incorporaremos\n'
              'la reproducción multimedia.',
              textAlign: TextAlign.center,
            ),

          ],
        ),
      ),
    );
  }
}
