package com.patos.vendas.service;

import com.patos.vendas.exception.NotFound;
import com.patos.vendas.interfaces.RankingInterface;
import com.patos.vendas.model.Cliente;
import com.patos.vendas.model.Pato;
import com.patos.vendas.model.Venda;
import com.patos.vendas.model.Vendedor;
import com.patos.vendas.model.dto.ClienteDTO;
import com.patos.vendas.model.dto.DadosVendaDTO;
import com.patos.vendas.model.dto.VendaDTO;
import com.patos.vendas.repositorie.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private PatoService patoService;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteService clienteService;

    public Venda cadastraVenda(VendaDTO dto, List<Long> patosId){

        Cliente cliente = clienteService.getClienteById(dto.getClienteId());

        Vendedor vendedor = vendedorService.getVendedorById(dto.getVendedorId());

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setVendedor(vendedor);
        List<Pato> patos = patoService.findAllById(patosId);

        if (patos.isEmpty()) {
            throw new RuntimeException("Nenhum dos patos fornecidos foi encontrado.");
        }

        for (Pato pato : patos) {
            if (pato.getVenda() != null) {
                throw new RuntimeException("O pato de ID " + pato.getId() + " já foi vendido!");
            }
        }



        BigDecimal total = BigDecimal.ZERO;
        for (Pato pato : patos) {
            Long quantidadeFilhos = patoService.countFilhos(pato) != null ? patoService.countFilhos(pato) : 0L;

            BigDecimal preco;
            if (quantidadeFilhos == 1) {
                preco = BigDecimal.valueOf(50.00);
            } else if (quantidadeFilhos >= 2) {
                preco = BigDecimal.valueOf(25.00);
            } else {
                preco = BigDecimal.valueOf(70.00);
            }

            total = total.add(preco);
        }

        boolean clienteElegivelDesconto = clienteService.getClienteById(cliente.getId()).getIselegivel();

        if (clienteElegivelDesconto) {
            BigDecimal desconto = total.multiply(BigDecimal.valueOf(0.20)); // 20% de desconto
            venda.setDescontoAplicado(desconto);
            total = total.subtract(desconto);
        } else {
            venda.setDescontoAplicado(BigDecimal.ZERO);
        }
        venda.setTotal(total);
        venda.setDataVenda(Timestamp.from(Instant.now()));

        Venda vendaSalva = vendaRepository.save(venda);


        for (Pato pato : patos) {
            pato.setVenda(vendaSalva);
            this.patoService.update(pato.getId(), pato);
        }

        return vendaSalva;

    }

    public List<DadosVendaDTO> listVendas() {
        return this.vendaRepository.findAll().stream()
                .map(venda -> modelMapper.map(venda, DadosVendaDTO.class))
                .collect(Collectors.toList());
    }


    public Page<RankingInterface> rankingVendedoresService(Date initialDate, Date finalDate, Pageable pageable) {
        if (initialDate == null || finalDate == null) {
            return vendaRepository.rankingVendedorGeral(pageable);
        }
        return vendaRepository.rankingVendedor(initialDate, finalDate, pageable);
    }

}
