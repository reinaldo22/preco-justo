package com.patos.vendas.repositorie;

import com.patos.vendas.model.Cliente;
import com.patos.vendas.model.Pato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PatoRepository extends JpaRepository<Pato, Long> {

    @Query("SELECT coalesce(MAX(p.profundidade), 0) FROM Pato p WHERE p.mae.id = :maeId")
    BigDecimal findMaxProfundidadeByMaeId(@Param("maeId") Long maeId);


    Long countByMaeId(Long id);
}
