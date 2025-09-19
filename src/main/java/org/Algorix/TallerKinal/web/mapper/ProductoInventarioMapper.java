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

    @Mapping(source = "idProveedor", target = "proveedor.id_proveedor")
    @Mapping(source = "name",target = "nombre")
    @Mapping(source = "description",target = "descripcion")
    @Mapping(source = "idCategoria",target = "categoria.id_categoria")
    @Mapping(source = "specification",target = "especificacion")
    @Mapping(source = "unitPrice",target = "precioUnitario")
    @Mapping(source = "currentStock",target = "stockActual")
    @Mapping(source = "minimumStock",target = "stockMinimo")
    @Mapping(source = "idMarca",target = "marca.id_marca")
    @Mapping(source = "entryDate",target = "fechaEntrada")
    ProductoInventarioEntity toEntity(ProductoInventarioDto d);


    void modificarEntityFromDto(ModProductoInventarioDto modProductoInventarioDto, @MappingTarget ProductoInventarioEntity productoInventarioEntity);
}