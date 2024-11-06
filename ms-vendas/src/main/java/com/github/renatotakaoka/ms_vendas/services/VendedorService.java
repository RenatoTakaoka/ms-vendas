package com.github.renatotakaoka.ms_vendas.services;

import com.github.renatotakaoka.ms_vendas.dtos.VendedorDTO;
import com.github.renatotakaoka.ms_vendas.exceptions.DatabaseException;
import com.github.renatotakaoka.ms_vendas.exceptions.ResourceNotFoundException;
import com.github.renatotakaoka.ms_vendas.models.Vendedor;
import com.github.renatotakaoka.ms_vendas.repositories.VendedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository repository;

    @Transactional(readOnly = true)
    public List<VendedorDTO> findAll() {
        return repository.findAll().stream().map(VendedorDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public VendedorDTO findById(Long id) {
        Vendedor vendedor = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new VendedorDTO(vendedor);
    }

    @Transactional
    public VendedorDTO insert(VendedorDTO dto) {
        Vendedor vendedor = new Vendedor();
        copyDtoToEntity(dto, vendedor);
        vendedor = repository.save(vendedor);
        return new VendedorDTO(vendedor);
    }

    @Transactional
    public VendedorDTO update(Long id, VendedorDTO dto) {
        try {
            Vendedor vendedor = repository.getReferenceById(id);
            copyDtoToEntity(dto, vendedor);
            vendedor = repository.save(vendedor);
            return new VendedorDTO(vendedor);
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

    private void copyDtoToEntity(VendedorDTO dto, Vendedor entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
    }

}
