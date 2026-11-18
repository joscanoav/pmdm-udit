
//
// MODELO CONTENIDO
//
// Esta clase representa un contenido de nuestra plataforma UDITflix.
// En esta versión el catálogo se ha convertido en una pequeña
// plataforma de streaming (películas y series), pero mantenemos
// los campos que ya existían (tipo y duración).
//

class Contenido {

  // Identificador único de cada contenido.
  final int id;

  // Título que aparecerá en el catálogo.
  final String titulo;

  // Descripción breve del contenido.
  final String descripcion;

  // Género o tipo: ciencia ficción, comedia, thriller...
  final String tipo;

  // Duración aproximada o número de temporadas.
  final String duracion;

  // Categoría principal: 'Película' o 'Serie'.
  final String categoria;

  // URL pública de la imagen de portada.
  final String imagenUrl;

  // Constructor: permite crear nuevos objetos Contenido.
  const Contenido({
    required this.id,
    required this.titulo,
    required this.descripcion,
    required this.tipo,
    required this.duracion,
    required this.categoria,
    required this.imagenUrl,
  });
}
