package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.persistence.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductoInventarioMapper {

    @Mappings({
        @Mapping(target = "proveedor", source = "idProveedor", qualifiedByName = "proveedorFromId"),
        @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "categoriaFromId"),
        @Mapping(target = "marca", source = "idMarca", qualifiedByName = "marcaFromId")
    })
    ProductoInventarioEntity toEntity(ProductoInventarioDto d);

    @Mappings({
        @Mapping(target = "idProveedor", source = "proveedor", qualifiedByName = "idFromProveedor"),
        @Mapping(target = "idCategoria", source = "categoria", qualifiedByName = "idFromCategoria"),
        @Mapping(target = "idMarca", source = "marca", qualifiedByName = "idFromMarca")
    })
    ProductoInventarioDto toDto(ProductoInventarioEntity e);

    @Named("proveedorFromId")
    default ProveedorEntity proveedorFromId(Long id) {
        if (id == null) return null;
        ProveedorEntity p = new ProveedorEntity();
        p.id_proveedor = id;
        return p;
    }

    @Named("categoriaFromId")
    default CategoriaProductoEntity categoriaFromId(Long id) {
        if (id == null) return null;
        CategoriaProductoEntity c = new CategoriaProductoEntity();
        c.id_categoria = id;
        return c;
    }

    @Named("marcaFromId")
    default MarcaProductoEntity marcaFromId(Long id) {
        if (id == null) return null;
        MarcaProductoEntity m = new MarcaProductoEntity();
        m.id_marca = id;
        return m;
    }

    @Named("idFromProveedor")
    default Long idFromProveedor(ProveedorEntity e) {
        return e == null ? null : e.id_proveedor;
    }

    @Named("idFromCategoria")
    default Long idFromCategoria(CategoriaProductoEntity e) {
        return e == null ? null : e.id_categoria;
    }

    @Named("idFromMarca")
    default Long idFromMarca(MarcaProductoEntity e) {
        return e == null ? null : e.id_marca;
    }
}

