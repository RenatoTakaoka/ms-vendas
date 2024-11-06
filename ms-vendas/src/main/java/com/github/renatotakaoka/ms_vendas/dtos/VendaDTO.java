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
public class VendaDTO {

    private Long id;
    @NotNull(message = "Valor total é necessário")
    private Double total;
    private LocalDate data;
    private Double comissao;
    private String status;
    private VendedorDTO vendedorDTO;

    public VendaDTO(Venda venda) {
        id = venda.getId();
        total = venda.getTotal();
        data = venda.getData();
        comissao = venda.getComissao();
        status = venda.getStatus();
    }

}
