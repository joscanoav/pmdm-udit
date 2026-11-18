
//
// PANTALLA DEL REPRODUCTOR
//
// Versión visual del reproductor: todavía no reproduce vídeos reales.
// Mantenemos el mensaje de que la reproducción llegará en una
// próxima versión.
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
      backgroundColor: const Color(0xFF0B0B10),
      appBar: AppBar(
        title: const Text('Reproductor UDITflix'),
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(24),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              // Icono provisional de reproducción.
              Container(
                padding: const EdgeInsets.all(24),
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  color: Theme.of(context).colorScheme.primary.withValues(alpha: 0.18),
                ),
                child: Icon(
                  Icons.play_circle_fill,
                  size: 96,
                  color: Theme.of(context).colorScheme.primary,
                ),
              ),

              const SizedBox(height: 24),

              // Título del contenido seleccionado.
              Text(
                contenido.titulo,
                textAlign: TextAlign.center,
                style: const TextStyle(
                  fontSize: 22,
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
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
                style: TextStyle(color: Colors.white70),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
