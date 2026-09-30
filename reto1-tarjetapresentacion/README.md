# Reto 1 · Tarjeta de Presentación Profesional

**Módulo:** 0489 · Programación Multimedia y Dispositivos Móviles
**Autor:** Jorge Oscanoa
**Tecnología:** Kotlin + Jetpack Compose (Android nativo)
**RA vinculado:** RA1 · Tecnologías de desarrollo para dispositivos móviles

## 📱 Qué es esta app

Una tarjeta de presentación digital (estilo Linktree) que muestra una foto de perfil, un nombre, un rol profesional y un botón que enlaza directamente al perfil de GitHub del autor.

> *Sustituye las capturas de abajo por las tuyas antes de entregar.*

<!-- ![Captura de la app](captura.png) -->

## 🎯 Objetivo del reto

Partir de un proyecto Android base y modificarlo para construir una aplicación funcional propia, aplicando los conceptos vistos en clase: estructura de un proyecto Kotlin, componentes visuales de Jetpack Compose, gestión de recursos (imágenes e icono) y control de versiones con Git.

## 🛠️ Componentes y conceptos utilizados

| Componente / concepto | Para qué se usa en esta app |
|---|---|
| `Column` | Organiza los elementos en vertical (foto, nombre, rol, botón) |
| `Image` + `clip(CircleShape)` | Muestra la foto de perfil recortada en círculo |
| `Text` | Nombre y rol profesional |
| `Spacer` | Separación entre elementos |
| `Button` + `Intent` | Al pulsar, abre el navegador en el perfil de GitHub |
| `res/drawable` | Carpeta donde vive la imagen de perfil |
| `res/mipmap` (Image Asset Studio) | Icono personalizado de la app, sustituyendo al robot de Android por defecto |
| `strings.xml` (`app_name`) | Nombre visible de la app bajo el icono, en el móvil |

## 🚀 Cómo ejecutar el proyecto

1. Clonar o abrir el proyecto en Android Studio.
2. Esperar a que sincronice Gradle.
3. Ejecutar (▶) sobre un emulador o un dispositivo Android real con la depuración USB activada.

## 🧠 Qué he aprendido

*(Completar antes de entregar — esta sección es la "memoria breve" del reto)*

- Cómo se estructura un proyecto Android/Kotlin con Jetpack Compose.
- Cómo importar y organizar imágenes en `res/drawable`.
- Cómo usar `Column`, `Image`, `Text`, `Spacer` y `Button` para maquetar una pantalla.
- Cómo cambiar el icono de la app con Image Asset Studio (capa de fondo y capa de primer plano).
- Cómo cambiar el nombre visible de la app en `strings.xml`, sin tocar el nombre del proyecto.
- Cómo lanzar una URL externa desde un botón usando `Intent` + `Uri`.

## 🐞 Dificultades y cómo las resolví

*(Completar antes de entregar)*

- Ejemplo: *"Al pegar código sin escribirlo, Android Studio no reconocía `Column`, `Image`, etc. Lo resolví usando Alt+Enter sobre cada palabra en rojo para que el IDE añadiera el import correspondiente."*

## 📂 Estructura del proyecto

```
app/src/main/java/.../MainActivity.kt   → pantalla principal (Compose)
app/src/main/res/drawable/              → imagen de perfil
app/src/main/res/mipmap-*/              → icono de la app
app/src/main/res/values/strings.xml     → nombre visible de la app
```

## 🔗 Enlace

- GitHub: [github.com/joscanoav](https://github.com/joscanoav)
