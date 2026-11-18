
//
// PANTALLA DE FICHA
//
// Esta pantalla recibe un objeto Contenido desde el catálogo.
//
// Su responsabilidad es mostrar toda la información
// correspondiente al elemento seleccionado.
//

import 'package:flutter/material.dart';

import '../models/contenido.dart';
import 'reproductor_screen.dart';

class FichaScreen extends StatelessWidget {

  // Contenido que recibimos desde la pantalla anterior.
  final Contenido contenido;

  // Constructor de la pantalla.
  // required obliga a proporcionar un contenido.
  const FichaScreen({
    super.key,
    required this.contenido,
  });

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      // Barra superior con el título seleccionado.
      appBar: AppBar(
        title: Text(contenido.titulo),
      ),

      // Contenido principal.
      body: Padding(

        padding: const EdgeInsets.all(20),

        child: Column(

          crossAxisAlignment: CrossAxisAlignment.start,

          children: [

            // Imagen provisional representada con un icono.
            Container(
              width: double.infinity,
              height: 180,
              decoration: BoxDecoration(
                color: Colors.deepPurple.shade100,
                borderRadius: BorderRadius.circular(12),
              ),
              child: const Icon(
                Icons.movie,
                size: 80,
                color: Colors.deepPurple,
              ),
            ),

            const SizedBox(height: 20),

            // Título del contenido.
            Text(
              contenido.titulo,
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),

            const SizedBox(height: 12),

            // Tipo de contenido.
            Text(
              'Tipo: ${contenido.tipo}',
              style: const TextStyle(fontSize: 16),
            ),

            const SizedBox(height: 8),

            // Duración.
            Text(
              'Duración: ${contenido.duracion}',
              style: const TextStyle(fontSize: 16),
            ),

            const SizedBox(height: 20),

            // Descripción completa.
            Text(
              contenido.descripcion,
              style: const TextStyle(fontSize: 16),
            ),

            const Spacer(),

            // Botón para abrir el reproductor.
            SizedBox(
              width: double.infinity,
              child: ElevatedButton.icon(

                icon: const Icon(Icons.play_arrow),

                label: const Text('Ver contenido'),

                onPressed: () {

                  // Abrimos la pantalla de reproducción.
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => ReproductorScreen(
                        contenido: contenido,
                      ),
                    ),
                  );

                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
