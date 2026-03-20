# Airport Management App (Android)

Aplicación Android desarrollada en Kotlin que permite gestionar información relacionada con un entorno de aeropuerto mediante operaciones CRUD completas. Implementa arquitectura MVVM y persistencia local con Room.

## 🚀 Funcionalidades

* Visualización de listado de registros
* Ordenación dinámica de los datos
* Alta de nuevos registros con validaciones:

  * Campos obligatorios
  * Identificador único
  * Restricciones de formato
* Edición de registros existentes (con restricciones)
* Eliminación con confirmación
* Gestión de estado vacío (sin datos)
* Diálogos de error y validación
* Notificaciones tras inserción de datos

## 🧱 Arquitectura

* MVVM (Model - View - ViewModel)
* Repository Pattern
* Separación clara de capas

## 🗄️ Persistencia

* Base de datos local con Room
* DAO para acceso a datos
* Gestión reactiva del estado

## 🖥️ Interfaz

* Jetpack Compose
* Pantallas principales:

  * Listado
  * Alta / edición

## 🔔 Notificaciones

* Canal de notificaciones
* Aviso al usuario tras crear un nuevo registro

## ⚙️ Tecnologías utilizadas

* Kotlin
* Jetpack Compose
* Room
* Android Architecture Components

## 📦 Instalación

```bash
git clone https://github.com/pablogfdev/app-android.git
```

Abrir en Android Studio y ejecutar.

## 📄 Licencia

MIT License
