// DATOS SIMULADOS DE UDITFLIX

// En esta primera versión no tenemos una API ni una base de datos.
// Por eso utilizamos una lis de objetos creada manualmente

// Más adelante sustituiremos estos datos por información
// que recibiremos desde el servidor mediante una API REST

import '../models/contenido.dart';


// Lista de contenidos que aparecerán en nuestra aplicación.
// Cada elemento de lista es un objeto de tipo Contenido

const List<Contenido> contenidos = [
    
    Contenido(
        id: 1,
        titulo: 'Introducción a Flutter',
        descripcion: 'Aprende a crear aplicaciones multiplataforma',
        tipo: 'Video',
        duracion: '15 minutos',
    ),
    Contenido(
        id: 2,
        titulo: 'Desarrollo con Java',
        descripcion: 'Introducción a la programación orientada a obejtos',
        tipo: 'Video',
        duracion: '25 minutos',
    ),

        Contenido(
        id: 3,
        titulo: 'El mundo de los cómics',
        descripcion: 'Una colección de historias y aventuras ilustradas',
        tipo: 'Cómic',
        duracion: '10 páginas',
    ),

    Contenido(
        id: 4,
        titulo: 'Programación de videojuegos',
        descripcion: 'Descubre los fundamentos de la lógica de videojuegos',
        tipo: 'Juego',
        duracion: '20 minutos',
    ),   

];