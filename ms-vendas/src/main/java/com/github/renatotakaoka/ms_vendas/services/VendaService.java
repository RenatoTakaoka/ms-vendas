package com.github.renatotakaoka.ms_vendas.services;

import com.github.renatotakaoka.ms_vendas.dtos.VendaDTO;
import com.github.renatotakaoka.ms_vendas.dtos.VendaInsertDTO;
import com.github.renatotakaoka.ms_vendas.exceptions.DatabaseException;
import com.github.renatotakaoka.ms_vendas.exceptions.ResourceNotFoundException;
import com.github.renatotakaoka.ms_vendas.models.Venda;
import com.github.renatotakaoka.ms_vendas.repositories.VendaRepository;
import com.github.renatotakaoka.ms_vendas.repositories.VendedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private VendedorRepository vendedorRepository;

    private final Double comissao = 0.1;

    @Transactional(readOnly = true)
    public List<VendaDTO> findAll() {
        return repository.findAll().stream().map(VendaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public VendaDTO findById(Long id) {
        Venda venda = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new VendaDTO(venda);
    }

    @Transactional
    public VendaDTO insert(VendaInsertDTO dto) {
        Venda venda = new Venda();
        copyDtoToEntity(dto, venda);
        venda = repository.save(venda);
        return new VendaDTO(venda);
    }

    @Transactional
    public VendaDTO update(Long id, VendaInsertDTO dto) {
        try {
            Venda venda = repository.getReferenceById(id);
            copyDtoToEntity(dto, venda);
            venda = repository.save(venda);
            return new VendaDTO(venda);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(VendaInsertDTO dto, Venda entity) {
        entity.setTotal(dto.getTotal());
        entity.setData(dto.getData());
        entity.setVendedor(vendedorRepository.getReferenceById(dto.getVendedorDTO().getId()));
        Double comissaoTotal = dto.getTotal() * comissao;
        entity.setComissao(comissaoTotal);
        String status = "";
        if(dto.getTotal() >= 30000) {
            status = "ALTA";
        } else if (dto.getTotal() >= 15000) {
            status = "MEDIA";
        } else {
            status = "BAIXA";
        }
        entity.setStatus(status);
    }

}
