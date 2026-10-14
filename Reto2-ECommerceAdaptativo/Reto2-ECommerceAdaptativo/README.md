# Reto 2 · E-Commerce Adaptativo (Responsive UI)

**Módulo:** 0489 · Programación Multimedia y Dispositivos Móviles
**Autor:** [Tu Nombre y Apellidos]
**Tecnología:** Kotlin + Jetpack Compose (Android nativo)
**RA vinculado:** RA1 y RA2 · Interfaces adaptables y limitaciones del dispositivo

## 📱 Producto Final (Capturas)
*(Borra este texto y añade debajo dos capturas de pantalla de tu aplicación funcionando: una en el emulador de MÓVIL en vertical, y otra en el emulador de TABLET o móvil en horizontal, demostrando que la cuadrícula se adapta).*

*(Guarda tus capturas en la carpeta `img/capturas/` y enlázalas así: `![Móvil](img/capturas/movil.png)`).*

[AÑADE TU CAPTURA DE MÓVIL AQUÍ]
[AÑADE TU CAPTURA DE TABLET AQUÍ]

### Esquema de referencia
Así debería comportarse tu cuadrícula: mismas tarjetas, mismo código, más columnas cuando hay más espacio.

![Esquema responsive: móvil en vertical (2 columnas) y tablet en horizontal (4 columnas)](img/esquema-responsive.png)

## 🎯 Objetivo del reto
Comprender la fragmentación de pantallas en Android y resolver los problemas de visualización en distintos perfiles de dispositivos. Pasar de un diseño estático (una lista infinita que se deforma) a una interfaz *responsive* (una cuadrícula inteligente) utilizando componentes de Jetpack Compose que calculan el espacio disponible en tiempo real.

## 🛠️ Componentes y conceptos utilizados

| Componente / Concepto | Para qué se usa en esta app |
| :--- | :--- |
| **LazyVerticalGrid** | Sustituye a `LazyColumn` para crear una cuadrícula en lugar de una lista simple. |
| **GridCells.Adaptive** | Calcula automáticamente cuántas columnas caben en la pantalla indicando solo el tamaño mínimo por tarjeta. |
| **Card** | Crea un contenedor elevado y con bordes redondeados para agrupar la imagen y los datos de cada producto. |
| **ContentScale.Crop** | Asegura que la imagen llene el espacio asignado recortando los bordes en lugar de deformarse al rotar la pantalla. |
| **maxLines & TextOverflow** | Fuerza a que los textos largos ocupen una sola línea y añade puntos suspensivos (`...`) para no descuadrar las tarjetas. |
| **Mock Data (Datos)** | Uso de una `data class` y una lista local para simular la información antes de conectar una base de datos real. |

## 🧐 Reflexión y Pensamiento Crítico
*(Responde a estas preguntas brevemente con tus propias palabras. Te servirán para la defensa oral).*

1. **Sobre la adaptabilidad:** ¿Por qué hemos usado `GridCells.Adaptive(minSize = ...)` en lugar de fijar el número de columnas con `GridCells.Fixed(2)`? ¿Qué ventaja tiene en el mundo real?
   > *[Tu respuesta aquí]*

2. **Sobre el diseño UI:** ¿Qué ocurría con las tarjetas si el nombre de un producto era muy largo y ocupaba dos líneas mientras los demás ocupaban una? ¿Cómo lo hemos solucionado?
   > *[Tu respuesta aquí]*

3. **Sobre las imágenes:** ¿Por qué es importante usar `ContentScale.Crop` en la imagen del producto cuando cambiamos el ancho de la tarjeta al rotar la pantalla?
   > *[Tu respuesta aquí]*

4. **Visión de futuro:** Ahora mismo nuestros productos están escritos directamente en el código (*hardcodeados*). ¿Qué crees que necesitaría nuestra app en el futuro para que esta tienda mostrara productos reales de internet?
   > *[Tu respuesta aquí]*

## 🚀 Cómo ejecutar el proyecto
1. Clonar el repositorio y abrir en Android Studio.
2. Ejecutar (▶) primero en un emulador de móvil (ej. Pixel 6).
3. Girar la pantalla del móvil a horizontal o ejecutar en un emulador de Tablet para comprobar la adaptación de la cuadrícula.

## 🐞 Dificultades y cómo las resolví
*(Opcional: Si te atascaste en algo durante el reto, explica aquí qué fue y cómo lo solucionaste).*
- *[Explica aquí tu dificultad]*
