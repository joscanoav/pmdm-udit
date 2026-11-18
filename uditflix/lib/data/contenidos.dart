
//
// DATOS SIMULADOS DE UDITFLIX
//
// No tenemos una API ni una base de datos: utilizamos una lista
// de objetos creada manualmente con 6 contenidos de ejemplo.
// Las portadas son URLs públicas de imágenes de Internet.
// Si no hay conexión, la aplicación muestra un diseño alternativo.
//

import '../models/contenido.dart';

// Lista de contenidos que aparecerán en nuestra aplicación.
// Cada elemento de la lista es un objeto de tipo Contenido.

const List<Contenido> contenidos = [

  Contenido(
    id: 1,
    titulo: 'Stranger Things',
    descripcion: 'Un grupo de amigos descubre un secreto que altera para siempre su pequeña ciudad.',
    tipo: 'Ciencia ficción',
    duracion: '4 temporadas',
    categoria: 'Serie',
    imagenUrl: 'https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn8AIqMGskD.jpg',
  ),

  Contenido(
    id: 2,
    titulo: 'Wednesday',
    descripcion: 'Wednesday Addams investiga un caso que mantiene en vilo a toda su academia.',
    tipo: 'Misterio',
    duracion: '2 temporadas',
    categoria: 'Serie',
    imagenUrl: 'https://image.tmdb.org/t/p/w500/9PFonBhy4cQy7Jz20NpMygczOkv.jpg',
  ),

  Contenido(
    id: 3,
    titulo: 'La casa de papel',
    descripcion: 'Un atraco imposible convierte a un grupo de desconocidos en leyendas.',
    tipo: 'Thriller',
    duracion: '5 temporadas',
    categoria: 'Serie',
    imagenUrl: 'https://image.tmdb.org/t/p/w500/reEMJA1uzscCbkpeRJeTT2bjqUp.jpg',
  ),

  Contenido(
    id: 4,
    titulo: 'Oppenheimer',
    descripcion: 'La historia del físico que cambió para siempre el rumbo de la humanidad.',
    tipo: 'Drama histórico',
    duracion: '3 h',
    categoria: 'Película',
    imagenUrl: 'https://image.tmdb.org/t/p/w500/ptpr0kGAckfQkJeJIt8st5dglvd.jpg',
  ),

  Contenido(
    id: 5,
    titulo: 'Barbie',
    descripcion: 'Barbie descubre el mundo real y todo lo que hay detrás de la famosa muñeca.',
    tipo: 'Comedia',
    duracion: '1 h 54 min',
    categoria: 'Película',
    imagenUrl: 'https://image.tmdb.org/t/p/w500/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg',
  ),

  Contenido(
    id: 6,
    titulo: 'Spider-Man: Across the Spider-Verse',
    descripcion: 'Miles Morales viaja por el multiverso y conoce a otros héroes araña.',
    tipo: 'Animación',
    duracion: '2 h 20 min',
    categoria: 'Película',
    imagenUrl: 'https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg',
  ),

];
