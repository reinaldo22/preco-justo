package com.patos.vendas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente", schema = "public")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="nome", unique = true)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "createdat")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "iselegivel")
    private Boolean iselegivel = false;

    @Column(name = "status")
    private Boolean status = true;

}
