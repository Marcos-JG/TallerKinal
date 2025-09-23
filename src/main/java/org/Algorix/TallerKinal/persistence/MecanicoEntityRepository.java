package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.MecanicoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMecanicoDto;
import org.Algorix.TallerKinal.dominio.exception.MecanicoNoExiste;
import org.Algorix.TallerKinal.dominio.repository.MecanicoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudMecanico;
import org.Algorix.TallerKinal.persistence.entity.MecanicoEntity;
import org.Algorix.TallerKinal.web.mapper.MecanicoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MecanicoEntityRepository implements MecanicoRepository {

    private final CrudMecanico crudMecanico;
    private final MecanicoMapper mecanicoMapper;

    public MecanicoEntityRepository(CrudMecanico crudMecanico, MecanicoMapper mecanicoMapper) {
        this.crudMecanico = crudMecanico;
        this.mecanicoMapper = mecanicoMapper;
    }

    @Override
    public List<MecanicoDto> listarMecanico() {
        return this.mecanicoMapper.toDto(this.crudMecanico.findAll());
    }

    @Override
    public MecanicoDto buscarMecanicoPorCodigo(Long codigo) {
        MecanicoEntity mecanicoEntity = this.crudMecanico.findById(codigo).orElse(null);
        if (mecanicoEntity == null) {
            throw new MecanicoNoExiste(codigo);
        }
        return this.mecanicoMapper.toDto(mecanicoEntity);
    }

    @Override
    public void eliminarMecanico(Long codigo) {
        MecanicoEntity mecanicoEntity = this.crudMecanico.findById(codigo).orElse(null);
        if (mecanicoEntity == null) {
            throw new MecanicoNoExiste(codigo);
        }
        this.crudMecanico.deleteById(codigo);
    }

    @Override
    public MecanicoDto editarMecanico(Long codigo, ModMecanicoDto modMecanicoDto) {
        MecanicoEntity mecanico = this.crudMecanico.findById(codigo).orElse(null);
        if (mecanico == null) {
            throw new MecanicoNoExiste(codigo);
        }
        this.mecanicoMapper.modificarEntityFromDto(modMecanicoDto, mecanico);
        return this.mecanicoMapper.toDto(this.crudMecanico.save(mecanico));
    }

    @Override
    public MecanicoDto guardarMecanico(MecanicoDto mecanicoDto) {
        MecanicoEntity mecanico = this.mecanicoMapper.toEntity(mecanicoDto);
        this.crudMecanico.save(mecanico);
        return this.mecanicoMapper.toDto(mecanico);
    }
}
