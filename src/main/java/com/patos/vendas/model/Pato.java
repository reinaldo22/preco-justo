package com.patos.vendas.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pato", schema = "public")
public class Pato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="nome", unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "mae_id")
    private Pato mae;

    @Column(name = "data_cadastro")
    @CreationTimestamp
    private Timestamp data_cadastro;

    @Column(name = "profundidade")
    private BigDecimal profundidade;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = true)
    @JsonBackReference
    private Venda venda;
}
