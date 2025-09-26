package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.DetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModDetalleUsoProductoDto;
import org.Algorix.TallerKinal.persistence.entity.DetalleUsoProductoEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleUsoProductoMapper {

    @Mapping(source = "trabajoRealizado.id_trabajo", target = "idTrabajoRealizado")
    @Mapping(source = "productoInventario.idProducto", target = "idProductoInventario")
    @Mapping(source = "cantidadUsada", target = "usedQuantity")
    @Mapping(source = "precioUnitario", target = "unitPrice")
    @Mapping(source = "subtotal", target = "subtotal")
    DetalleUsoProductoDto toDto(DetalleUsoProductoEntity e);

    List<DetalleUsoProductoDto> toDto(Iterable <DetalleUsoProductoEntity> entities);

    @Mapping(source = "idTrabajoRealizado", target = "trabajoRealizado.id_trabajo")
    @Mapping(source = "idProductoInventario", target = "productoInventario.idProducto")
    @Mapping(source = "usedQuantity", target = "cantidadUsada")
    @Mapping(source = "unitPrice", target = "precioUnitario")
    @Mapping(source = "subtotal", target = "subtotal")
    DetalleUsoProductoEntity toEntity(DetalleUsoProductoDto d);

    @Mapping(source = "idTrabajoRealizado", target = "trabajoRealizado.id_trabajo")
    @Mapping(source = "idProductoInventario", target = "productoInventario.idProducto")
    @Mapping(source = "usedQuantity", target = "cantidadUsada")
    @Mapping(source = "unitPrice", target = "precioUnitario")
    @Mapping(source = "subtotal", target = "subtotal")
    void modificarEntityFromDto(ModDetalleUsoProductoDto modDetalleUsoProductoDto, @MappingTarget DetalleUsoProductoEntity detalleUsoProductoEntity);
}
