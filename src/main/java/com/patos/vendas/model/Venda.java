package com.patos.vendas.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venda_patos", schema = "public")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Column(name = "data_venda")
    @CreationTimestamp
    private Timestamp dataVenda;

    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Pato> patos;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "desconto_aplicado", nullable = false)
    private BigDecimal descontoAplicado = BigDecimal.ZERO;
}
