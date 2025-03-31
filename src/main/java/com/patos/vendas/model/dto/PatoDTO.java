package com.patos.vendas.model.dto;

import com.patos.vendas.model.Pato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatoDTO {
    private String nome;
    private Long mae;


    public PatoDTO(Pato pato) {
        this.nome = pato.getNome();
        this.mae = (pato.getMae() != null) ? pato.getMae().getId() : null;
    }
}
