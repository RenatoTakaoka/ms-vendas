package com.github.renatotakaoka.ms_vendas.dtos;

import com.github.renatotakaoka.ms_vendas.models.Venda;
import jakarta.validation.constraints.NotNull;
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
    private Double total;
    @NotNull(message = "Data é necessária")
    private LocalDate data;

    public VendaInsertDTO(Venda entity) {

    }

}
