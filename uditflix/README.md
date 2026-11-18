# UDITflix · Evolución de la aplicación con IA

## Objetivo

Partiendo del proyecto inicial de UDITflix, vamos a evolucionar la aplicación Flutter mediante el uso de OpenCode y el modelo Big Pickle.

El objetivo es ampliar el catálogo multimedia e incorporar nuevas funcionalidades que permitan experimentar con la reproducción de vídeos y el desarrollo de videojuegos dentro de una aplicación Flutter.

---

## 1. Comprobar el proyecto inicial

El proyecto inicial se encuentra en la rama `main`.

Antes de realizar modificaciones, ejecutaremos la aplicación para comprobar su funcionamiento:

```bash
flutter pub get
flutter run
```

---

## 2. Cambiar de rama para visualizar el primer avance

La rama del avance visual incorpora:
- Catálogo de películas y series.
- Imágenes de portada cargadas desde Internet.
- Sección de contenido destacado.
- Organización por categorías.
- Tarjetas visuales.
- Pantallas de detalle.
- Navegación entre pantallas.
- Diseño inspirado en plataformas de streaming.

### 2.1. Consultar las ramas disponibles

```bash
git branch
```

La rama actual aparecerá marcada con un asterisco (`*`).

### 2.2. Descargar los cambios y cambiar de rama

Si la rama está publicada en GitHub, actualizamos la información:

```bash
git fetch
```

Después, cambiamos a la rama del avance visual:

```bash
git switch avance-catalogo-visual
```

> Si la rama tiene otro nombre, utiliza el nombre real de la rama creada para este avance.

### 2.3. Ejecutar la aplicación

```bash
flutter pub get
flutter run
```

### 2.4. Volver a la versión inicial

```bash
git switch main
```

Para volver a la versión mejorada:

```bash
git switch avance-catalogo-visual
```

---

## 3. Continuar el desarrollo utilizando OpenCode

1. Abrir el proyecto UDITflix en Visual Studio Code.
2. Abrir OpenCode desde el terminal o desde la extensión correspondiente.
3. Seleccionar el modelo Big Pickle.
4. Comprobar que OpenCode está trabajando sobre la carpeta raíz del proyecto.
5. Proporcionar el siguiente prompt.

### Prompt de desarrollo

```text
Analiza el proyecto Flutter actual de UDITflix.

El proyecto ya dispone de un catálogo visual de películas y series,
con imágenes de Internet, contenido destacado, fichas de detalle
y navegación entre pantallas.

Quiero continuar evolucionando esta aplicación sin reconstruirla
desde cero y sin eliminar las funcionalidades existentes.

El objetivo es incorporar dos nuevas funcionalidades:

1. REPRODUCCIÓN DE VÍDEOS

Implementa un reproductor de vídeo funcional dentro de la aplicación.

Requisitos:
- Utiliza Flutter y el paquete video_player si es necesario.
- Integra la reproducción dentro de la aplicación.
- Permite reproducir y pausar el vídeo.
- Muestra controles básicos del reproductor.
- Permite visualizar el vídeo a pantalla completa si resulta apropiado.
- Incorpora al menos un vídeo de demostración.
- Puedes utilizar un vídeo MP4 público de Internet o un recurso local.
- Si utilizas un vídeo externo, comprueba que la URL sea accesible.
- Mantén la navegación actual desde la ficha de una película
  hacia la pantalla de reproducción.

2. VIDEOJUEGO INTEGRADO EN UDITFLIX

Incorpora una nueva sección denominada UDIT Arcade.

Implementa un pequeño videojuego 2D inspirado en el estilo
Flappy Bird, utilizando Flutter y Flame si resulta apropiado.

El videojuego debe incluir:
- Un personaje que se mueva por la pantalla.
- Control del personaje mediante pulsaciones.
- Obstáculos que se desplacen.
- Detección de colisiones.
- Contador de puntuación.
- Pantalla de Game Over.
- Botón para reiniciar la partida.

El juego debe ser sencillo, funcional y adecuado para
una demostración educativa.

INTEGRACIÓN:
- Añade un acceso al videojuego desde la aplicación UDITflix.
- Mantén el catálogo y las pantallas existentes.
- No reconstruyas el proyecto desde cero.
- Reutiliza la arquitectura y los modelos existentes.
- Mantén un diseño visual coherente con UDITflix.
- No añadas funcionalidades innecesarias.

VERIFICACIÓN:
1. Ejecuta flutter pub get.
2. Ejecuta flutter analyze.
3. Ejecuta flutter test.
4. Comprueba que la aplicación compila.
5. Corrige los errores que hayas introducido.

Al finalizar, explica:
- Qué archivos se han creado y modificado.
- Qué paquetes se han añadido.
- Cómo funciona el reproductor.
- Cómo funciona el videojuego.
- Cómo acceder a ambas funcionalidades desde la aplicación.
```

---

## 4. Generar el APK de Android

### Paso 1. Comprobar el proyecto

```bash
flutter pub get
flutter analyze
flutter test
```

### Paso 2. Generar el APK

```bash
flutter build apk --release
```

El APK se generará en:

```text
build/app/outputs/flutter-apk/app-release.apk
```

---

## 5. Instalar la aplicación en un móvil Android

### Opción A. Mediante cable USB

1. Activar las opciones de desarrollador en el dispositivo Android.
2. Activar la depuración USB.
3. Conectar el móvil al ordenador mediante USB.
4. Comprobar que Flutter detecta el dispositivo:

```bash
flutter devices
```

5. Instalar la aplicación:

```bash
flutter install
```

### Opción B. Transferir el APK

1. Localizar `build/app/outputs/flutter-apk/app-release.apk`.
2. Copiarlo al móvil mediante USB, Bluetooth o cualquier otro método de transferencia.
3. Abrir el archivo APK desde el dispositivo Android.
4. Autorizar la instalación de aplicaciones desde esa fuente, si Android lo solicita.
5. Instalar y ejecutar UDITflix.

---

## Resultado esperado

Al finalizar, tendremos una aplicación UDITflix que incorpora:

- Catálogo multimedia.
- Imágenes de películas y series.
- Pantallas de detalle.
- Reproducción de vídeos.
- Una sección de videojuegos.
- Un videojuego 2D funcional.
- Una APK instalable en Android.

El proyecto permitirá experimentar con Flutter tanto en el desarrollo de interfaces móviles como en la reproducción de contenido multimedia y el desarrollo de videojuegos.
