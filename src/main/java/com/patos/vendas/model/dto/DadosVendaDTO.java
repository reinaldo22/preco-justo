package com.patos.vendas.model.dto;


import com.patos.vendas.model.Cliente;
import com.patos.vendas.model.Pato;
import com.patos.vendas.model.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class DadosVendaDTO {

    private Long id;
    private Cliente cliente;
    private List<Pato> patos;
    private Date dataVenda;
    private BigDecimal total;

    public DadosVendaDTO(Venda venda) {
       this.id = venda.getId();
        this.total = venda.getTotal();
        this.dataVenda = venda.getDataVenda();
        this.patos = venda.getPatos();
        this.cliente = venda.getCliente();

    }

}
