package com.github.renatotakaoka.ms_vendas.dtos;

import com.github.renatotakaoka.ms_vendas.models.Venda;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendaInsertDTO {

    private Long id;
    @NotNull(message = "Valor total é necessário")
    @Positive(message = "Total deve ser positivo")
    private Double total;
    @NotNull(message = "Data é necessária")
    private LocalDate data;
    private VendedorDTO vendedorDTO;

    public VendaInsertDTO(Venda entity) {
        id = entity.getId();
        total = entity.getTotal();
        data = entity.getData();
        vendedorDTO = new VendedorDTO(entity.getVendedor());
    }

}
