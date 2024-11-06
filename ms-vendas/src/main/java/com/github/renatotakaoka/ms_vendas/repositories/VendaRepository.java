package com.github.renatotakaoka.ms_vendas.repositories;

import com.github.renatotakaoka.ms_vendas.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
