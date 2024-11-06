package com.github.renatotakaoka.ms_vendas.repositories;

import com.github.renatotakaoka.ms_vendas.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
