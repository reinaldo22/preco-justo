package com.patos.vendas.controller;

import com.patos.vendas.model.dto.ClienteDTO;
import com.patos.vendas.model.dto.PatoDTO;
import com.patos.vendas.service.PatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patos")
public class PatoController {


    @Autowired
    private PatoService patoService;


    @PostMapping
    public ResponseEntity<PatoDTO> create(@RequestBody PatoDTO dto){

    return new ResponseEntity<>(patoService.create(dto), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<PatoDTO>> getAll() {
        return ResponseEntity.ok(patoService.getAll());
    }
}
