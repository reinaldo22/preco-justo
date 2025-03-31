package com.patos.vendas.repositorie;

import com.patos.vendas.model.Venda;
import com.patos.vendas.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Object> findByCpf(String cpf);

    Optional<Object> findByMatricula(String matricula);

    @Query("SELECT COUNT(v) > 0 FROM Venda v WHERE v.vendedor.id = :vendedorId")
    boolean existsByVendas(@Param("vendedorId") Long vendedorId);
}
