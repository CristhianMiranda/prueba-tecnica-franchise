# Aplicación de Gestión de Franquicias

Esta es una aplicación **Spring Boot** diseñada para gestionar franquicias, sucursales y productos. Utiliza **MySQL** como base de datos. Este documento detalla cómo ejecutar la aplicación de dos formas:
- **Con Docker** (usando `docker-compose`)
- **Sin Docker** (ejecutando directamente con Java y MySQL locales).

## Requisitos Previos

### General:
- **Java 17** o superior
- **Gradle 8.10.2**
- **Mysql 8.0.40** o superior

### Para Docker:
- **Docker**

---

## Ejecución de la Aplicación con Docker

### Paso 1: Clonar el Repositorio
Clona el proyecto a tu máquina local:

```bash
git clone https://github.com/CristhianMiranda/prueba-tecnica.git
cd franchise-management-app
```


### Paso 2: Construir el Proyecto
Ejecuta el siguiente comando para construir el proyecto con Gradle:
```
./gradlew build
```

### Paso 3: Ejecutar la Aplicación con Docker
Para ejecutar la aplicación junto con MySQL utilizando Docker, solo necesitas ejecutar:
```
docker-compose up
```

### Paso 4: Probar la API con Postman o Insomnia
En la raíz del proyecto, encontrarás un archivo llamado Api.postman_collection.json. Este archivo contiene las peticiones necesarias para interactuar con la API. Importa este archivo en Postman o Insomnia y realiza las peticiones correspondientes a los siguientes endpoints:

- Agregar una nueva franquicia.
- Agregar una nueva sucursal a una franquicia.
- Agregar un nuevo producto a una sucursal.
- Eliminar un producto de una sucursal.
- Modificar el stock de un producto.
- Obtener el producto con más stock por sucursal para una franquicia específica. Este endpoint devuelve un listado de productos, indicando a qué sucursal pertenece cada uno.
- Actualizar el nombre de una franquicia.
- Actualizar el nombre de una sucursal.
- Actualizar el nombre de un producto.


---

## Ejecución de la Aplicación sin Docker

### Paso 1: Configurar la Base de Datos MySQL
1. Asegúrate de tener MySQL instalado y corriendo en tu máquina local.
2. Crea una base de datos llamada company:
```sql
CREATE DATABASE company;
```
3. Usa la base de datos recién creada:
```sql
USE company;
```
### Paso 2: Importa los scripts
1. Importa los scripts de inicialización que se encuentran en el archivo init.sql, ubicado en la raíz del proyecto.
```sql
-- init.sql

CREATE TABLE franchise (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

CREATE TABLE branch (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        franchise_id BIGINT NOT NULL,
                        FOREIGN KEY (franchise_id) REFERENCES franchise(id)
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         stock_quantity INT NOT NULL,
                         branch_id BIGINT NOT NULL,
                         FOREIGN KEY (branch_id) REFERENCES branch(id)
);
```
### Paso 5: Configurar las Credenciales de MySQL
1. El archivo src/main/resources/application.properties ya está configurado con las credenciales por defecto:
```sql
spring.datasource.url=jdbc:mysql://localhost:3306/company
spring.datasource.username=root
spring.datasource.password=root
```
Si usas otras credenciales, asegúrate de modificarlas según corresponda en el archivo application.properties:
```sql
spring.datasource.url=jdbc:mysql://localhost:3306/company
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### Paso 6: Construir el Proyecto
1. Una vez que hayas configurado la base de datos y las credenciales de MySQL
```sql
./gradlew build
```

### Paso 7: Ejecutar el Proyecto
1. Una vez que el proyecto haya sido construido, dirígete a la carpeta franchise-core-app/build/libs y ejecuta el siguiente comando desde una terminal en esa ubicación:
```sql
java -jar franchise-core-app-0.0.1-SNAPSHOT.jar
```

### Paso 8: Probar la API con Postman o Insomnia
En la raíz del proyecto, encontrarás un archivo llamado Api.postman_collection.json. Este archivo contiene las peticiones necesarias para interactuar con la API. Importa este archivo en Postman o Insomnia y realiza las peticiones correspondientes a los siguientes endpoints:

- Agregar una nueva franquicia.
- Agregar una nueva sucursal a una franquicia.
- Agregar un nuevo producto a una sucursal.
- Eliminar un producto de una sucursal.
- Modificar el stock de un producto.
- Obtener el producto con más stock por sucursal para una franquicia específica. Este endpoint devuelve un listado de productos, indicando a qué sucursal pertenece cada uno.
- Actualizar el nombre de una franquicia.
- Actualizar el nombre de una sucursal.
- Actualizar el nombre de un producto.

