
//
// PORTADA DE RED (WIDGET REUTILIZABLE)
//
// Muestra una imagen desde una URL pública usando Image.network.
// Añade un indicador de carga y un diseño alternativo por si
// la imagen no puede descargarse (por ejemplo, sin Internet).
// Así la aplicación nunca se rompe por un problema de red.
//

import 'package:flutter/material.dart';

class PortadaNetwork extends StatelessWidget {

  final String url;

  // Alineación de la imagen cuando se recorta (útil en la cabecera).
  final AlignmentGeometry alignment;

  const PortadaNetwork({
    super.key,
    required this.url,
    this.alignment = Alignment.center,
  });

  @override
  Widget build(BuildContext context) {
    return Image.network(
      url,
      fit: BoxFit.cover,
      alignment: alignment,
      // Si falla la descarga, mostramos un diseño alternativo.
      errorBuilder: (context, error, stackTrace) => const _PortadaAlternativa(),
      // Mientras se descarga, mostramos el diseño alternativo
      // con un indicador de progreso encima.
      loadingBuilder: (context, child, progreso) {
        if (progreso == null) {
          return child;
        }

        final total = progreso.expectedTotalBytes;

        return Stack(
          alignment: Alignment.center,
          children: [
            const _PortadaAlternativa(),
            CircularProgressIndicator(
              value: total != null && total > 0
                  ? progreso.cumulativeBytesLoaded / total
                  : null,
              color: const Color(0xFFE50914),
              strokeWidth: 2.5,
            ),
          ],
        );
      },
    );
  }
}

// Diseño alternativo: degradado oscuro con un icono de película.
class _PortadaAlternativa extends StatelessWidget {
  const _PortadaAlternativa();

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      decoration: const BoxDecoration(
        gradient: LinearGradient(
          colors: [Color(0xFF2C2C3A), Color(0xFF14141C)],
        ),
      ),
      child: const Icon(
        Icons.movie_rounded,
        color: Colors.white38,
        size: 48,
      ),
    );
  }
}
