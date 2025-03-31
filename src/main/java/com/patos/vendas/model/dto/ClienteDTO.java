package com.patos.vendas.model.dto;

import com.patos.vendas.model.Cliente;
import com.patos.vendas.model.Pato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private String nome;
    private String cpf;
    private boolean status;
    private boolean iselegivel;




    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.status = true;
        this.iselegivel = false;
    }


}
