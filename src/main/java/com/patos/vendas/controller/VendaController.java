package com.patos.vendas.controller;

import com.patos.vendas.model.Venda;
import com.patos.vendas.model.dto.ClienteDTO;
import com.patos.vendas.model.dto.DadosVendaDTO;
import com.patos.vendas.model.dto.PatoDTO;
import com.patos.vendas.model.dto.VendaDTO;
import com.patos.vendas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaServie;


    @PostMapping
    public ResponseEntity<Venda> create(@RequestBody VendaDTO dto){
        List<Long> patosIds = dto.getPatosIds();

        return new ResponseEntity<>(vendaServie.cadastraVenda(dto, patosIds), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DadosVendaDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(vendaServie.listVendas());
    }

    @GetMapping("/ranking")
    public ResponseEntity<Page<?>> rankingVendedores(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date initialDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date finalDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(vendaServie.rankingVendedoresService(initialDate, finalDate, pageable));
    }
}
