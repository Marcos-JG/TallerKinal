package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.DetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModDetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.exception.DetalleUsoProductoNoExiste;
import org.Algorix.TallerKinal.dominio.repository.DetalleUsoProductoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudDetalleUsoProducto;
import org.Algorix.TallerKinal.persistence.entity.DetalleUsoProductoEntity;
import org.Algorix.TallerKinal.web.mapper.DetalleUsoProductoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetalleUsoProductoEntityRepository implements DetalleUsoProductoRepository {

    private final CrudDetalleUsoProducto crudDetalleUsoProducto;
    private final DetalleUsoProductoMapper mapper;

    public DetalleUsoProductoEntityRepository(CrudDetalleUsoProducto crudDetalleUsoProducto, DetalleUsoProductoMapper mapper) {
        this.crudDetalleUsoProducto = crudDetalleUsoProducto;
        this.mapper = mapper;
    }

    @Override
    public List<DetalleUsoProductoDto> obtenerTodo() {
        return this.mapper.toDto(this.crudDetalleUsoProducto.findAll());
    }

    @Override
    public DetalleUsoProductoDto buscarPorCodigo(Long id) {
        DetalleUsoProductoEntity detalleUsoProductoEntity = crudDetalleUsoProducto.findById(id).orElse(null);
        if (detalleUsoProductoEntity == null) throw new DetalleUsoProductoNoExiste(id);{
        }
        return this.mapper.toDto(this.crudDetalleUsoProducto.findById(id).orElse(null));
    }

    @Override
    public DetalleUsoProductoDto guardarDetalle(DetalleUsoProductoDto detalleUsoProductoDto) {
        DetalleUsoProductoEntity detalle = this.mapper.toEntity(detalleUsoProductoDto);
        this.crudDetalleUsoProducto.save(detalle);
        return this.mapper.toDto(detalle);
    }

    @Override
    public DetalleUsoProductoDto modificarDetalle(Long id, ModDetalleUsoProductoDto modDetalleUsoProductoDto) {
        DetalleUsoProductoEntity detalle = this.crudDetalleUsoProducto.findById(id).orElse(null);
        if (detalle == null) {
            throw new DetalleUsoProductoNoExiste(id);
        }
        this.mapper.modificarEntityFromDto(modDetalleUsoProductoDto, detalle);
        return this.mapper.toDto(this.crudDetalleUsoProducto.save(detalle));
    }

    @Override
    public void eliminarDetalle(Long id) {
    DetalleUsoProductoEntity detalle = this.crudDetalleUsoProducto.findById(id).orElse(null);
    if (detalle == null) {
        throw new DetalleUsoProductoNoExiste(id);
    } else {
        this.crudDetalleUsoProducto.deleteById(id);
    }
    }
}
