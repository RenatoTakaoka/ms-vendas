package com.github.renatotakaoka.ms_vendas.controllers;

import com.github.renatotakaoka.ms_vendas.dtos.VendaDTO;
import com.github.renatotakaoka.ms_vendas.dtos.VendaInsertDTO;
import com.github.renatotakaoka.ms_vendas.services.VendaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {

    @Autowired
    private VendaService service;

    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll() {
        List<VendaDTO> vendaDTOS = service.findAll();
        return ResponseEntity.ok(vendaDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable Long id) {
        VendaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VendaDTO> insert(@RequestBody @Valid VendaInsertDTO dto) {
        VendaDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> update(@PathVariable @NotNull Long id,
                                          @RequestBody @Valid VendaInsertDTO dto) {
        VendaDTO response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
