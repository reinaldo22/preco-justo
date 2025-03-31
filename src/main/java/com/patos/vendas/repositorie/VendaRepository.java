package com.patos.vendas.repositorie;

import com.patos.vendas.interfaces.RankingInterface;
import com.patos.vendas.model.Pato;
import com.patos.vendas.model.Venda;
import com.patos.vendas.model.dto.VendaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface VendaRepository extends JpaRepository<Venda, Long> {


    @Query(value = """ 
        SELECT 
            v.vendedor_id,
            ven.nome AS nome_vendedor,
            COUNT(v.id) AS total_vendas,
            SUM(v.total) AS total_valor_vendido
        FROM venda_patos v
        JOIN vendedor ven ON v.vendedor_id = ven.id
        GROUP BY v.vendedor_id, ven.nome
        ORDER BY total_vendas DESC, total_valor_vendido DESC;
        """, nativeQuery = true)
    Page<RankingInterface> rankingVendedorGeral(Pageable pageable);

    @Query(value = """ 
            SELECT 
                v.vendedor_id,
                ven.nome AS nome_vendedor,
                COUNT(v.id) AS total_vendas,
                SUM(v.total) AS total_valor_vendido
            FROM venda_patos v
            JOIN vendedor ven ON v.vendedor_id = ven.id
            WHERE 
                (date_trunc('day', v.data_venda) BETWEEN :initialDate AND :finalDate)
            GROUP BY v.vendedor_id, ven.nome
            ORDER BY total_vendas DESC, total_valor_vendido DESC;
            """, nativeQuery = true)
    Page<RankingInterface> rankingVendedor(@Param("initialDate") Date initialDate,
                                           @Param("finalDate") Date finalDate,
                                           Pageable pageable);


}
