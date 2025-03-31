package com.patos.vendas.controller;

import com.patos.vendas.model.dto.ClienteDTO;
import com.patos.vendas.model.dto.VendedorDTO;
import com.patos.vendas.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {


    @Autowired
    private VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<VendedorDTO> create(@RequestBody @Validated VendedorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> getAll() {
        return ResponseEntity.ok(vendedorService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVendedor(@PathVariable Long id) {
        try {
            vendedorService.deletarVendedor(id);
            return ResponseEntity.ok("Vendedor deletado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
