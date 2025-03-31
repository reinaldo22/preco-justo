package com.patos.vendas.interfaces;



import java.math.BigDecimal;

public interface RankingInterface {

    Long getVendedor_id();

    String getNome_vendedor();

    BigDecimal getTotal_vendas();

    BigDecimal getTotal_valor_vendido();
}