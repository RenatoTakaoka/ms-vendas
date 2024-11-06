package com.github.renatotakaoka.ms_vendas.controllers;

import com.github.renatotakaoka.ms_vendas.dtos.VendedorDTO;
import com.github.renatotakaoka.ms_vendas.services.VendedorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService service;

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> findAll() {
        List<VendedorDTO> vendedoresDTOS = service.findAll();
        return ResponseEntity.ok(vendedoresDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorDTO> findById(@PathVariable Long id) {
        VendedorDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> insert(@RequestBody @Valid VendedorDTO dto) {
        VendedorDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> update(@PathVariable @NotNull Long id,
                                           @RequestBody @Valid VendedorDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
