# Platzi Market API Documentation

Se ha implementado la documentación de la API utilizando **OpenAPI (Swagger)**.

## Requisitos previos

Asegúrate de que tu IDE haya descargado las nuevas dependencias de Gradle.
Puedes forzar la actualización y compilar el proyecto con el siguiente comando en tu terminal:

```bash
./gradlew build
```

## Instrucciones para ver la documentación

1. Inicia la aplicación de Spring Boot. Puedes correrla desde tu IDE o ejecutando el siguiente comando:

```bash
./gradlew bootRun
```

2. Una vez que la aplicación esté en ejecución y lista para recibir peticiones, abre tu navegador web o un cliente HTTP e ingresa a una de las siguientes rutas:

- **Swagger UI (Interfaz visual e interactiva para probar los endpoints):**
  [http://localhost:8081/platzi-market/api/swagger-ui.html](http://localhost:8081/platzi-market/api/swagger-ui.html)
  
- **OpenAPI JSON (Estructura de la documentación cruda en formato JSON):**
  [http://localhost:8081/platzi-market/api/v3/api-docs](http://localhost:8081/platzi-market/api/v3/api-docs)

> **Nota:** Las direcciones anteriores toman en cuenta la configuración de tu aplicación, donde el puerto definido en `dev` es el **8081** y el `context-path` general de la aplicación es `/platzi-market/api`.
