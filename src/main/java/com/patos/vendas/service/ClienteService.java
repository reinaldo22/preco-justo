package com.patos.vendas.service;

import com.patos.vendas.exception.NotFound;
import com.patos.vendas.model.Cliente;
import com.patos.vendas.model.dto.ClienteDTO;
import com.patos.vendas.repositorie.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ClienteDTO create(ClienteDTO dto) {
        Cliente cliente = modelMapper.map(dto, Cliente.class);
        cliente = clienteRepository.save(cliente);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> getAll() {
        return clienteRepository.findAll().stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public void toggleStatus (Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFound("Cliente não encontrada com ID: " + id));

        cliente.setStatus(!cliente.getStatus());

        cliente = clienteRepository.saveAndFlush(cliente);
        modelMapper.map(cliente, ClienteDTO.class);
    }

    public void toggleElegivel (Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFound("Cliente não encontrada com ID: " + id));

        cliente.setIselegivel(!cliente.getIselegivel());

        cliente = clienteRepository.saveAndFlush(cliente);
        modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFound("Cliente não encontrado"));
    }
}
