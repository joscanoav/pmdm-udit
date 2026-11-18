
//
// PRUEBA BÁSICA DE LA APLICACIÓN UDITFLIX
//
// Comprueba que se muestran la cabecera y las secciones del catálogo,
// y que al pulsar una tarjeta se abre la ficha del contenido.
//

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:uditflix/main.dart';

void main() {

  testWidgets('UDITflix muestra su catálogo y navega a la ficha', (WidgetTester tester) async {

    // Usamos una pantalla alta para que ListView.builder construya
    // todas las secciones (solo crea los elementos visibles).
    await tester.binding.setSurfaceSize(const Size(1000, 2200));
    addTearDown(() => tester.binding.setSurfaceSize(null));

    await tester.pumpWidget(const UDITflixApp());

    // Comprobamos la cabecera y las secciones del catálogo.
    expect(find.text('UDITflix'), findsOneWidget);
    expect(find.text('Tendencias'), findsOneWidget);
    expect(find.text('Películas'), findsOneWidget);
    expect(find.text('Series'), findsOneWidget);

    // Pulsamos una tarjeta y comprobamos que se abre su ficha.
    await tester.tap(find.text('Oppenheimer').first);
    await tester.pumpAndSettle();

    expect(find.text('Categoría: Película'), findsOneWidget);
    expect(find.text('Volver'), findsOneWidget);
  });

}
