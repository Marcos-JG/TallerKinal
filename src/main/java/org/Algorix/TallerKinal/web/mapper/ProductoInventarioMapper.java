package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.persistence.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoInventarioMapper {

    @Mapping(source = "proveedor.id_proveedor", target = "idProveedor")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "categoria.id_categoria", target = "idCategoria")
    @Mapping(source = "especificacion", target = "specification")
    @Mapping(source = "precioUnitario", target = "unitPrice")
    @Mapping(source = "stockActual", target = "currentStock")
    @Mapping(source = "stockMinimo", target = "minimumStock")
    @Mapping(source = "marca.id_marca", target = "idMarca")
    @Mapping(source = "fechaEntrada", target = "entryDate")
    ProductoInventarioDto toDto(ProductoInventarioEntity e);

    public List<ProductoInventarioDto> toDto(Iterable<ProductoInventarioEntity> entities);

    // Corrige mapeos: usamos ids del DTO para poblar entidades en el target
    @Mapping(source = "idProveedor", target = "proveedor", qualifiedByName = "mapIdProveedorToProveedorEntity")
    @Mapping(source = "name",target = "nombre")
    @Mapping(source = "description",target = "descripcion")
    @Mapping(source = "idCategoria",target = "categoria", qualifiedByName = "mapIdCategoriaToCategoriaEntity")
    @Mapping(source = "specification",target = "especificacion")
    @Mapping(source = "unitPrice",target = "precioUnitario")
    @Mapping(source = "currentStock",target = "stockActual")
    @Mapping(source = "minimumStock",target = "stockMinimo")
    @Mapping(source = "idMarca",target = "marca", qualifiedByName = "mapIdMarcaToMarcaEntity")
    @Mapping(source = "entryDate",target = "fechaEntrada")
    ProductoInventarioEntity toEntity(ProductoInventarioDto d);

    @Named("mapIdProveedorToProveedorEntity")
    default ProveedorEntity mapIdProveedorToProveedorEntity(Long idProveedor) {
        if (idProveedor == null) return null;
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(idProveedor);
        return proveedor;
    }

    @Named("mapIdCategoriaToCategoriaEntity")
    default CategoriaProductoEntity mapIdCategoriaToCategoriaEntity(Long idCategoria) {
        if (idCategoria == null) return null;
        CategoriaProductoEntity categoria = new CategoriaProductoEntity();
        categoria.setId_categoria(idCategoria);
        return categoria;
    }

    @Named("mapIdMarcaToMarcaEntity")
    default MarcaProductoEntity mapIdMarcaToMarcaEntity(Long idMarca) {
        if (idMarca == null) return null;
        MarcaProductoEntity marca = new MarcaProductoEntity();
        marca.setId_marca(idMarca);
        return marca;
    }

    // Corrige mapeos también para el DTO de modificación
    @Mapping(source = "idProveedor", target = "proveedor", qualifiedByName ="mapIdProveedorToProveedorEntity" )
    @Mapping(source = "name",target = "nombre")
    @Mapping(source = "description",target = "descripcion")
    @Mapping(source = "specification",target = "especificacion")
    @Mapping(source = "idCategoria",target = "categoria", qualifiedByName = "mapIdCategoriaToCategoriaEntity")
    @Mapping(source = "unitPrice",target = "precioUnitario")
    @Mapping(source = "currentStock",target = "stockActual")
    @Mapping(source = "minimumStock",target = "stockMinimo")
    @Mapping(source = "idMarca",target = "marca", qualifiedByName = "mapIdMarcaToMarcaEntity")
    @Mapping(source = "entryDate",target = "fechaEntrada")
    void modificarEntityFromDto(ModProductoInventarioDto modProductoInventarioDto, @MappingTarget ProductoInventarioEntity productoInventarioEntity);
}