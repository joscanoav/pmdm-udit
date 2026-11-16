// Importamos Flutterr para utilizar sus componentes visuales
import 'package:flutter/material.dart';
// Importamos la lista de contenidos simulados.
import '../data/contenidos.dart';

// Creamos la pantalla de catálogo
class CatalogoScreen extends StatelessWidget {
    // Constructor de la pantalla
    const CatalogoScreen({super.key});

    // Método que construye la interfaz visual
    @override
    Widget build(BuildContext context) {
        // Scaffold proporciona la estructura básica de una pantalla.
        return Scaffold(
            // Barra superior de la aplicación
            appBar: AppBar(
                // Texto que aparece en la barra superior
                title: const Text('UDITflix'),
                // Color de fondo de barra
                backgroundColor: Colors.deepPurple,
                // Color de texto y de los iconos de barra.
                foregroundColor: Colors.white,
            ),
            // ListView.builder crea una lista desplazable

            body: ListView.builder(
                // Añadir un margen interior de 16 px
                padding: const EdgeInsets.all(16),
                // Indica el contenido de nuestra lista
                itemCount: contenidos.length,
                itemBuilder: (context, index) {
                    //Recuperamos el contenido correspondiente
                    final contenido = contenidos[index];

                    // Card crea una tarjeta visual
                    return Card(
                        // Listtile organiza el contenido dentro de la tarjeta
                        child: ListTile(
                            // Icono que aparece a la izquierda de la tarjeta
                            leading: const Icon(
                                Icons.play_circle_fill,
                                color: Colors.deepPurple,
                            ),

                            // Mostramos el título

                            title: Text(contenido.titulo),
                            // Mostramos el tipo de contenido
                            subtitle: Text(contenido.tipo),
                            // Icono que aparece a la derecha de la tarjeta
                            trailing: const Icon(Icons.arrow_forward_ios),
                        ),
                    );
                }
            ),

        );
    }
}


