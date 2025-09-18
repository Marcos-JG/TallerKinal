package org.Algorix.TallerKinal.web.mapper;

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
    @Mapping(source = "especificacion", target = "specifications")
    @Mapping(source = "precioUnitario", target = "unitPrice")
    @Mapping(source = "stockActual", target = "actualStock")
    @Mapping(source = "stockMinimo", target = "minStock")
    @Mapping(source = "marca.id_marca", target = "idMarca")
    @Mapping(source = "fechaEntrada", target = "dateEntry")


    ProductoInventarioDto toDto(ProductoInventarioEntity e);
    public List<ProductoInventarioDto> toDto(Iterable<ProductoInventarioEntity> entities);


    ProductoInventarioEntity toEntity(ProductoInventarioDto d);
}