package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.productoWebDto;
import org.Algorix.TallerKinal.dominio.repository.ProductoRepository;
import org.Algorix.TallerKinal.dominio.service.ProductoInventarioService;
import org.Algorix.TallerKinal.persistence.entity.CategoriaProductoEntity;
import org.Algorix.TallerKinal.persistence.entity.MarcaProductoEntity;
import org.Algorix.TallerKinal.persistence.entity.ProductoInventarioEntity;
import org.Algorix.TallerKinal.persistence.entity.ProveedorEntity;
import org.Algorix.TallerKinal.web.mapper.ProductoInventarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Data
@ViewScoped
public class InventarioWeb implements Serializable {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoInventarioService productoInventarioService;

    private List<productoWebDto> inventario;   // Lista para la tabla
    private ProductoInventarioEntity productoInventario; // Producto que se edita/crea en el modal
    private ProductoInventarioMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(InventarioWeb.class);

    @PostConstruct
    public void init() {
        cargarDatos();
        nuevoProducto(); // inicializar un producto vacío
    }

    // Carga productos para la tabla
    public void cargarDatos() {
        this.inventario = this.productoRepository.obtenerProductos();
        this.inventario.forEach(productos -> logger.info(productos.toString()));
    }

    // Inicializa un nuevo producto (para el modal "Nuevo producto")
    public void nuevoProducto() {
        this.productoInventario = new ProductoInventarioEntity();
        this.productoInventario.setProveedor(new ProveedorEntity());
        this.productoInventario.setCategoria(new CategoriaProductoEntity());
        this.productoInventario.setMarca(new MarcaProductoEntity());
    }
/*
    // Guarda el producto desde el modal
    public void guardarProducto() {
        try {
            // Mapeamos el DTO web MechanicView entity
            ProductoInventarioEntity entity = mapper.toEntity(productoInventario);

            // Guardamos con el servicio
            ProductoInventarioDto dtoGuardado = productoInventarioService.guardarProductos(entity);

            // Refrescamos la lista
            cargarDatos();

            logger.info("Producto guardado con éxito: {}", dtoGuardado);
        } catch (Exception e) {
            logger.error("Error al guardar producto", e);
        }
    }
*/
}
