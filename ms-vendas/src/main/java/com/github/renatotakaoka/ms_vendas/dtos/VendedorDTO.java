package com.github.renatotakaoka.ms_vendas.dtos;

import com.github.renatotakaoka.ms_vendas.models.Venda;
import com.github.renatotakaoka.ms_vendas.models.Vendedor;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendedorDTO {

    private Long id;
    @NotBlank(message = "Nome é necessário")
    private String nome;
    @NotBlank(message = "Email é necessário")
    private String email;

    public VendedorDTO(Vendedor entity) {
        id = entity.getId();
        nome = entity.getEmail();
        email = entity.getEmail();
    }

}
