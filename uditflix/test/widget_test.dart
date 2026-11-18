
//
// PRUEBA BÁSICA DE LA APLICACIÓN UDITFLIX
//
// Comprueba que la aplicación se puede construir
// y que muestra el título y un contenido del catálogo.
//

import 'package:flutter_test/flutter_test.dart';
import 'package:uditflix/main.dart';

void main() {

  testWidgets('UDITflix muestra su catálogo', (WidgetTester tester) async {

    // Construimos la aplicación dentro del entorno de pruebas.
    await tester.pumpWidget(const UDITflixApp());

    // Comprobamos que aparece el título de la aplicación.
    expect(find.text('UDITflix'), findsOneWidget);

    // Comprobamos que aparece un contenido del catálogo.
    expect(find.text('Introducción a Flutter'), findsOneWidget);

  });

}
