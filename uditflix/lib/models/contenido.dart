
//
// MODELO CONTENIDO
//
// Esta clase representa un contenido de nuestra plataforma UDITflix.
// Por ejemplo: una película, un cómic o un videojuego.
//
// En programación orientada a objetos, una clase define
// qué características tendrán los objetos que creemos.
//

class Contenido {

  // Identificador único de cada contenido.
  final int id;

  // Título que aparecerá en el catálogo.
  final String titulo;

  // Descripción del contenido.
  final String descripcion;

  // Tipo de contenido: vídeo, cómic o juego.
  final String tipo;

  // Duración aproximada o tiempo estimado.
  final String duracion;

  // Constructor: permite crear nuevos objetos Contenido.
  const Contenido({
    required this.id,
    required this.titulo,
    required this.descripcion,
    required this.tipo,
    required this.duracion,
  });
}
