package com.patos.vendas.model.dto;

import com.patos.vendas.model.Pato;
import com.patos.vendas.model.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {
    private Long clienteId;
    private Long vendedorId;
    private List<Long> patosIds;
    private BigDecimal total;
    private BigDecimal descontoAplicado;
    private Timestamp dataVenda;



    public VendaDTO(Venda venda) {
        this.clienteId = venda.getCliente().getId();
        this.vendedorId = venda.getVendedor().getId();
        this.patosIds = venda.getPatos().stream().map(pato -> pato.getId()).collect(Collectors.toList());
        this.total = venda.getTotal();
        this.descontoAplicado = venda.getDescontoAplicado();
        this.dataVenda = venda.getDataVenda();
    }
}
