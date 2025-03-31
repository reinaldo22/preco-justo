package com.patos.vendas.model.dto;


import com.patos.vendas.model.Cliente;
import com.patos.vendas.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorDTO {

    private String nome;
    private String cpf;
    private String matricula;
    private boolean status;

    public VendedorDTO(Vendedor vendedor) {
        this.nome = vendedor.getNome();
        this.cpf = vendedor.getCpf();
        this.matricula = vendedor.getMatricula();
        this.status = vendedor.getStatus();
    }

}
