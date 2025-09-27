# Taller Kinal - API REST

Pagina principal
http://localhost:8083/taller-kinal/api/index.xhtml

Documentacion Swagger ui
http://localhost:8083/taller-kinal/api/swagger-ui/index.html#/

## URL Base del Servidor

localhost:8083/taller-kinal/api

## Endpoints por Entidad

### Vehículos

/v1/vehiculos

### DetalleUsoProducto

/v1/detalleusoproducto

### Proveedores

/v1/proveedores

### Trabajos Realizados

/v1/trabajosRealizados

### Productos en Inventario

/v1/productos-inventarios

### Mecánicos

/v1/mecanicos

### Marcas de Producto

/v1/marcas-productos

### Clientes

/v1/clientes

### Citas

/v1/citas

### Categorías de Producto

/v1/categorias-producto

### Administradores

/v1/administradores

### Reportes

/v1/reportes

## Mapeo CRUD por Entidad

| Entidad                | Endpoint                          | Métodos Soportados                |
|------------------------|-----------------------------------|-----------------------------------|
| Vehículos              | `/v1/vehiculos`                   | `GET`, `POST`, `PUT`, `DELETE`   |
| Proveedores            | `/v1/proveedores`                 | `GET`, `POST`, `PUT`, `DELETE`   |
| Trabajos Realizados    | `/v1/trabajosRealizados`          | `GET`, `POST`, `PUT`, `DELETE`   |
| Productos Inventario   | `/v1/productos-inventarios`       | `GET`, `POST`, `PUT`, `DELETE`   |
| Mecánicos              | `/v1/mecanicos`                   | `GET`, `POST`, `PUT`, `DELETE`   |
| Marcas Producto        | `/v1/marcas-productos`            | `GET`, `POST`, `PUT`, `DELETE`   |
| Clientes               | `/v1/clientes`                    | `GET`, `POST`, `PUT`, `DELETE`   |
| Citas                  | `/v1/citas`                       | `GET`, `POST`, `PUT`, `DELETE`   |
| Categorías Producto    | `7v1/categorias-productos`        | `GET`, `POST`, `PUT`, `DELETE`   |
| Administradores        | `/v1/administradores`             | `GET`, `POST`, `PUT`, `DELETE`   |
| DetalleUsoProducto     | `/v1/detalleusoproductos`         | `GET`, `POST`, `PUT`, `DELETE`   |
| Reportes               | `/v1/reportes`                    | `GET`, `POST`, `PUT`, `DELETE`   |


## Integrantes del Proyecto

- **Byron Pineda**
- **Emiliano Herrera**
- **Marcos Cruz**

## Notas
- Asegúrate de que el usuario esté correctamente escrito para que corra en application.properties.
- Asegúrate de que el servidor esté corriendo en `localhost:8083` antes de realizar solicitudes.


