package com.patos.vendas.repositorie;
import org.springframework.data.jpa.repository.JpaRepository;
import com.patos.vendas.model.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
}
