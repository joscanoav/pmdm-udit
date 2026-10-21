import 'package:flutter/material.dart';

// main(): el punto de arranque de CUALQUIER app Flutter.
//Siempre existe, un runApp() = onCreate() en Kotlin
void main () {
  runApp(const BurgerShopApp());
}

//APP RAÍZ

class BurgerShopApp extends StatelessWidget {

  // const + super.key: es nuestro constructor - Flutter pide siempre ese comando
  const BurgerShopApp({super.key});
  //build(): el método que TODO widget debe tener. Aqui
  //es donde dibujas todo lo que se ve en la pantalla.

  @override
  Widget build(BuildContext context) {
    //MaterialApp: el "contenedor raíz" de toda la appt
    return MaterialApp(
      debugShowCheckedModeBanner: false, // quita la cinta roja
      title: 'BurgerShop',

      theme: ThemeData(
        //COLOR DE LA APP: esta es la Única linea para cambiar el aspecto
        colorSchemeSeed: Colors.deepOrange,
        useMaterial3: true,

      ),
      home: const PantallaCatalogo(),// la primera pantalla que se ve al abrir el app
    );
  }
}

// MODELO DE DATOS
// En Dart no existe "data class" como en Kotlin: una clase normal
// con un constructor que recibe los campos para hacer el mismo trabajo


class Producto {
  // final: el valor no cambia una vez creado el objeto (val en Kotlin)
  final String nombre;
  final String precio; // Al modificar a carro de compras modificamos la variable
  final String imagenAsset;

  // Constructor: "required" obloga a pasar siempre estos tres datos
  // al crear un Producto
  Producto({
    required this.nombre,
    required this.precio,
    required this.imagenAsset,
});
}

// DATOS DE PRUEBA
// Los mismos prroductos en Kotlin , apuntando a los mismos Archivos
// final List<Producto> = una lista que no se puede reasignar, pero el cotenido si
// podriamos modificar

final List<Producto> catalogoHamburguesas = [

  Producto(
      nombre: 'Clasica con Queso',
      precio: '6,50 €',
      imagenAsset: 'assets/images/burger_clasica.jpg'
  ),

  Producto(
      nombre: 'BBQ Bacon',
      precio: '7,90 €',
      imagenAsset: 'assets/images/burger_bbq.jpg'
  ),

  Producto(
      nombre: 'Doble Carne',
      precio: '8,50 €',
      imagenAsset: 'assets/images/burger_doble.jpg'
  ),

  Producto(
      nombre: 'Vegetariana',
      precio: '7,20 €',
      imagenAsset: 'assets/images/burger_vegetariana.jpg'
  ),

  Producto(
      nombre: 'Picante Jalapeño',
      precio: '7,80 €',
      imagenAsset: 'assets/images/burger_picante.jpg'
  ),

  Producto(
      nombre: 'Pollo Crispy',
      precio: '6,90 €',
      imagenAsset: 'assets/images/burger_pollo.jpg'
  ),
];


//PANTALLA 1: CATALOGO RESPONSIVE (RETO 2)

class PantallaCatalogo extends StatelessWidget {
  const PantallaCatalogo({super.key});
  @override
  Widget build(BuildContext context){

    //Scaffold: La estructura standar de una pantalla Android/iOS

    return Scaffold(
      //Barra superior con el titulo
      appBar: AppBar(title: const Text('🍔 BurgerShop')),

      //LayoutBuilder:  nos da el ANCHO disponible de la pantalla
      body: LayoutBuilder(
        builder: (context, constraints) {
          // Mismo cálculo que GridCells.Adaptive(minSize= 250.dp)
          // "cuántas tarjetas de al menos 250 caben en el ancho dispoble
          const double anchoMinimno = 250;
          const double espacio = 16; // huevo entre las tarjetas
          final double anchoUtil = constraints.maxWidth - (espacio * 2);
          int columnas = ((anchoUtil + espacio) / (anchoMinimno + espacio)).floor();
          if (columnas < 1) columnas = 1; // como mínino 1 columna

          // GridView.builder: Equivalente de LazyVerticalGrid en Compose

          return GridView.builder(
            padding: const EdgeInsets.all(espacio), // margen alrededor de la rejilla
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: columnas, // movil vertical 1; horizontal 3
              mainAxisSpacing: espacio, // hueco vertical entre tarjetas
              crossAxisSpacing: espacio, // hueco horizontal entre columnas

              // ALTO fijo de cada tarjeta

              mainAxisExtent: 340,
            ),
            itemCount: catalogoHamburguesas.length, // cuantos elementos tiene la lista
            itemBuilder: (context, index){
              final producto = catalogoHamburguesas[index];
              return TarjetaProducto(producto: producto);
            },
          );
        },
      ),
    );
  }
}


//TARJETA DE PRODUCTO

class TarjetaProducto extends StatelessWidget{
  final Producto producto; // Este el dato para dibujar
  const TarjetaProducto({super.key, required this.producto});

  @override

  Widget build(BuildContext context) {
    // Card: una caja con sombra y bordes redondeados por defecto
    // Es igual a nuestro Card de Compose.
    return Card(
      margin: EdgeInsets.zero, // el huevo entre las tarjetas,
      clipBehavior: Clip.antiAlias, // recorta la imagen a las esquinas redondeadas

      // Column: apila elementos de arriba a abajo
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start, // alina el texto a la izquierda
        children: [
          //Expanded: La imagen ocupa todo el alto
          Expanded(
              child: Image.asset(
                producto.imagenAsset,
                width: double.infinity, // ocupa todo el ancho disponible
                fit: BoxFit.cover, // = ContentScale.Crop en Kotlin: recorta sin deformar

              ),
          ),

          //Segunda Column, con margen interior, para el texto y el boton
          Padding(
              padding: const EdgeInsets.all(12),
              child: Column(
                mainAxisSize: MainAxisSize.min, // ocupa solo lo que necesita su contenido
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    producto.nombre,
                    style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(height: 4), // Spacer(height) en kotling - hueco pequeño

                  Text(
                    producto.precio,
                    style:  TextStyle(
                        fontSize: 16,
                        color: Theme.of(context).colorScheme.primary, // color de acento del tema actual
                  ),
                  ),
                  const SizedBox(height: 8), // Spacer(height) en kotling - hueco pequeño

                  SizedBox(
                    width: double.infinity, // el boton ocupa todo el ancho
                    child: ElevatedButton(
                      onPressed: () {
                        // De momento no hace nada pero en consola dira Añadido al carrito
                        print('¡Añadido : ${producto.nombre}!');
                      },
                      child: const Text('Añadir al carrito'),
                    ),
                  )
                ],
              ),
          ),
        ],

      ),
    );
  }
}






