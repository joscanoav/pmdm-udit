
//
// PANTALLA DEL CATÁLOGO
//
// Muestra una sección destacada y varias filas de contenido:
// Tendencias, Películas y Series.
//
// Utilizamos:
// - Scaffold: estructura general de la pantalla.
// - AppBar: cabecera con el nombre UDITflix.
// - ListView.builder: lista vertical de secciones y listas horizontales
//   de tarjetas con imágenes de portada.
// - ClipRRect: bordes redondeados en las tarjetas.
//

import 'package:flutter/material.dart';

import '../data/contenidos.dart';
import '../models/contenido.dart';
import '../widgets/portada_network.dart';
import 'ficha_screen.dart';
import 'reproductor_screen.dart';

class CatalogoScreen extends StatelessWidget {

  const CatalogoScreen({super.key});

  // Preparamos las tres secciones del catálogo a partir de los datos.
  List<_Seccion> _construirSecciones() {
    return [
      _Seccion(titulo: 'Tendencias', contenidos: contenidos),
      _Seccion(
        titulo: 'Películas',
        contenidos: contenidos
            .where((contenido) => contenido.categoria == 'Película')
            .toList(),
      ),
      _Seccion(
        titulo: 'Series',
        contenidos: contenidos
            .where((contenido) => contenido.categoria == 'Serie')
            .toList(),
      ),
    ];
  }

  // Navegación a la ficha del contenido seleccionado.
  void _abrirFicha(BuildContext context, Contenido contenido) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => FichaScreen(contenido: contenido),
      ),
    );
  }

  // Navegación al reproductor provisional (sin vídeo real todavía).
  void _abrirReproductor(BuildContext context, Contenido contenido) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => ReproductorScreen(contenido: contenido),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final secciones = _construirSecciones();
    final destacado = contenidos.first;

    return Scaffold(

      // Cabecera con la identidad UDITflix.
      appBar: AppBar(
        title: const Text(
          'UDITflix',
          style: TextStyle(
            fontWeight: FontWeight.w900,
            letterSpacing: -0.5,
          ),
        ),
        actions: const [
          Padding(
            padding: EdgeInsets.only(right: 18),
            child: Icon(
              Icons.play_circle_fill,
              color: Color(0xFFE50914),
              size: 28,
            ),
          ),
        ],
      ),

      // Lista vertical: primero el destacado y después las secciones.
      body: ListView.builder(
        itemCount: secciones.length + 1,
        itemBuilder: (context, index) {
          if (index == 0) {
            return _PortadaDestacada(
              contenido: destacado,
              onReproducir: () => _abrirReproductor(context, destacado),
              onVerFicha: () => _abrirFicha(context, destacado),
            );
          }

          final seccion = secciones[index - 1];
          return _SeccionCatalogo(
            seccion: seccion,
            onSeleccionar: (contenido) => _abrirFicha(context, contenido),
          );
        },
      ),
    );
  }
}

// Clase pequeña para agrupar el título y los contenidos de una sección.
class _Seccion {
  final String titulo;
  final List<Contenido> contenidos;

  const _Seccion({required this.titulo, required this.contenidos});
}

// Contenido destacado de la pantalla principal.
class _PortadaDestacada extends StatelessWidget {
  final Contenido contenido;
  final VoidCallback onReproducir;
  final VoidCallback onVerFicha;

  const _PortadaDestacada({
    required this.contenido,
    required this.onReproducir,
    required this.onVerFicha,
  });

  @override
  Widget build(BuildContext context) {
    final primary = Theme.of(context).colorScheme.primary;

    return Container(
      height: 320,
      margin: const EdgeInsets.only(bottom: 16),
      child: Stack(
        fit: StackFit.expand,
        children: [
          // La misma portada, atenuada, sirve de fondo.
          Opacity(
            opacity: 0.35,
            child: PortadaNetwork(
              url: contenido.imagenUrl,
              alignment: Alignment.topCenter,
            ),
          ),

          // Degradado para integrar la imagen con el fondo oscuro.
          const DecoratedBox(
            decoration: BoxDecoration(
              gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [Color(0x330B0B10), Color(0xFF0B0B10)],
              ),
            ),
          ),

          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 18),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.end,
              children: [
                // Portada nítida a la izquierda.
                ClipRRect(
                  borderRadius: BorderRadius.circular(12),
                  child: SizedBox(
                    width: 108,
                    height: 162,
                    child: PortadaNetwork(url: contenido.imagenUrl),
                  ),
                ),
                const SizedBox(width: 14),

                // Texto y botones a la derecha.
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Container(
                        padding: const EdgeInsets.symmetric(
                          horizontal: 8,
                          vertical: 4,
                        ),
                        decoration: BoxDecoration(
                          color: primary,
                          borderRadius: BorderRadius.circular(4),
                        ),
                        child: const Text(
                          'DESTACADO HOY',
                          style: TextStyle(
                            fontSize: 10,
                            fontWeight: FontWeight.bold,
                            color: Colors.white,
                          ),
                        ),
                      ),
                      const SizedBox(height: 8),
                      Text(
                        contenido.titulo,
                        maxLines: 2,
                        overflow: TextOverflow.ellipsis,
                        style: const TextStyle(
                          fontSize: 24,
                          fontWeight: FontWeight.w900,
                          color: Colors.white,
                        ),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        '${contenido.categoria} · ${contenido.duracion}',
                        style: const TextStyle(
                          fontSize: 13,
                          color: Colors.white70,
                        ),
                      ),
                      const SizedBox(height: 6),
                      Text(
                        contenido.descripcion,
                        maxLines: 3,
                        overflow: TextOverflow.ellipsis,
                        style: const TextStyle(
                          fontSize: 12,
                          color: Colors.white70,
                        ),
                      ),
                      const SizedBox(height: 12),

                      // Wrap adapta los botones a pantallas estrechas.
                      Wrap(
                        spacing: 8,
                        runSpacing: 8,
                        children: [
                          FilledButton.icon(
                            onPressed: onReproducir,
                            icon: const Icon(Icons.play_arrow),
                            label: const Text('Reproducir'),
                            style: FilledButton.styleFrom(
                              backgroundColor: primary,
                            ),
                          ),
                          OutlinedButton.icon(
                            onPressed: onVerFicha,
                            icon: const Icon(Icons.info_outline),
                            label: const Text('Más info'),
                            style: OutlinedButton.styleFrom(
                              foregroundColor: Colors.white,
                              side: const BorderSide(color: Colors.white54),
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

// Una sección del catálogo con título y fila horizontal de tarjetas.
class _SeccionCatalogo extends StatelessWidget {
  final _Seccion seccion;
  final ValueChanged<Contenido> onSeleccionar;

  const _SeccionCatalogo({
    required this.seccion,
    required this.onSeleccionar,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Row(
            children: [
              Container(
                width: 4,
                height: 20,
                decoration: BoxDecoration(
                  color: Theme.of(context).colorScheme.primary,
                  borderRadius: BorderRadius.circular(2),
                ),
              ),
              const SizedBox(width: 8),
              Text(
                seccion.titulo,
                style: const TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                ),
              ),
            ],
          ),
        ),
        const SizedBox(height: 10),
        SizedBox(
          height: 182,
          child: ListView.builder(
            scrollDirection: Axis.horizontal,
            padding: const EdgeInsets.symmetric(horizontal: 16),
            itemCount: seccion.contenidos.length,
            itemBuilder: (context, index) {
              final contenido = seccion.contenidos[index];
              return Padding(
                padding: const EdgeInsets.only(right: 12),
                child: _TarjetaContenido(
                  contenido: contenido,
                  onTap: () => onSeleccionar(contenido),
                ),
              );
            },
          ),
        ),
        const SizedBox(height: 18),
      ],
    );
  }
}

// Tarjeta visual con la portada y el título del contenido.
class _TarjetaContenido extends StatelessWidget {
  final Contenido contenido;
  final VoidCallback onTap;

  const _TarjetaContenido({
    required this.contenido,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    final esPelicula = contenido.categoria == 'Película';

    return SizedBox(
      width: 120,
      child: InkWell(
        onTap: onTap,
        borderRadius: BorderRadius.circular(12),
        child: ClipRRect(
          borderRadius: BorderRadius.circular(12),
          child: Stack(
            children: [
              Positioned.fill(child: PortadaNetwork(url: contenido.imagenUrl)),

              // Degradado para que el título se lea sobre la imagen.
              const Positioned.fill(
                child: DecoratedBox(
                  decoration: BoxDecoration(
                    gradient: LinearGradient(
                      begin: Alignment.topCenter,
                      end: Alignment.bottomCenter,
                      colors: [Colors.transparent, Color(0xCC000000)],
                    ),
                  ),
                ),
              ),

              // Etiqueta de Película o Serie.
              Positioned(
                top: 6,
                left: 6,
                child: Container(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 5,
                    vertical: 2,
                  ),
                  decoration: BoxDecoration(
                    color: esPelicula
                        ? const Color(0xFFE50914)
                        : Colors.deepPurple,
                    borderRadius: BorderRadius.circular(4),
                  ),
                  child: Text(
                    esPelicula ? 'PELÍCULA' : 'SERIE',
                    style: const TextStyle(
                      fontSize: 8,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                ),
              ),

              Positioned(
                left: 8,
                right: 8,
                bottom: 8,
                child: Text(
                  contenido.titulo,
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  style: const TextStyle(
                    fontSize: 12,
                    fontWeight: FontWeight.bold,
                    color: Colors.white,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
