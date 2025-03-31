package com.patos.vendas.controller;

import com.patos.vendas.model.dto.ClienteDTO;
import com.patos.vendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Validated ClienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        clienteService.toggleStatus(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/desconto/{id}")
    public ResponseEntity<Void> isElegivel(@PathVariable Long id) {
        clienteService.toggleElegivel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return ResponseEntity.ok(clienteService.getAll());
    }
}
