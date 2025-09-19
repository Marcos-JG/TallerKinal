# Taller Kinal - API REST

Este proyecto expone un conjunto de endpoints RESTful para la gestión de un taller mecánico. A continuación se presenta el mapeo de solicitudes CRUD para cada una de las entidades disponibles en el sistema.

## URL Base del Servidor

localhost:8083/taller-kinal/api

## Endpoints por Entidad

### Vehículos

/v1/vehiculos

### Proveedores

/v1/proveedores

### Trabajos Realizados

/v1/trabajosRealizados

### Productos en Inventario

/v1/productos-inventario

### Mecánicos

/v1/mecanico

### Marcas de Producto

/v1/marcas-producto

### Clientes

/v1/clientes

### Citas

/v1/citas

### Categorías de Producto

/v1/categorias-producto

### Administradores


/v1/administradores

## 🗂️ Mapeo CRUD por Entidad

| Entidad                | Endpoint                          | Métodos Soportados                |
|------------------------|-----------------------------------|-----------------------------------|
| Vehículos              | `/v1/vehiculos`                   | `GET`, `POST`, `PUT`, `DELETE`   |
| Proveedores            | `/v1/proveedores`                 | `GET`, `POST`, `PUT`, `DELETE`   |
| Trabajos Realizados    | `/v1/trabajosRealizados`          | `GET`, `POST`, `PUT`, `DELETE`   |
| Productos Inventario   | `/v1/productos-inventario`        | `GET`, `POST`, `PUT`, `DELETE`   |
| Mecánicos              | `/v1/mecanico`                    | `GET`, `POST`, `PUT`, `DELETE`   |
| Marcas Producto        | `/v1/marcas-producto`             | `GET`, `POST`, `PUT`, `DELETE`   |
| Clientes               | `/v1/clientes`                    | `GET`, `POST`, `PUT`, `DELETE`   |
| Citas                  | `/v1/citas`                       | `GET`, `POST`, `PUT`, `DELETE`   |
| Categorías Producto    | `/v1/categorias-producto`         | `GET`, `POST`, `PUT`, `DELETE`   |
| Administradores        | `/v1/administradores`             | `GET`, `POST`, `PUT`, `DELETE`   |

## Integrantes del Proyecto

- **Byron Pineda**
- **Emiliano Herrera**
- **Marcos Cruz**

## Notas
- Asegúrate de que el usuario esté correctamente escrito para que corra en application.properties.
- Asegúrate de que el servidor esté corriendo en `localhost:8083` antes de realizar solicitudes.


