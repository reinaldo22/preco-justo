package com.patos.vendas.service;

import com.patos.vendas.exception.DuplicateKeyException;
import com.patos.vendas.exception.IllegalArgumentException;
import com.patos.vendas.exception.NotFound;
import com.patos.vendas.model.Vendedor;
import com.patos.vendas.model.dto.VendedorDTO;
import com.patos.vendas.repositorie.VendedorRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public VendedorDTO create(VendedorDTO dto) {

            vendedorRepository.findByCpf(dto.getCpf()).ifPresent(v -> {
                throw new DuplicateKeyException("Já existe um vendedor cadastrado com este CPF: " + dto.getCpf());
            });

            vendedorRepository.findByMatricula(dto.getMatricula()).ifPresent(v -> {
                throw new DuplicateKeyException("Já existe um vendedor cadastrado com esta matrícula: " + dto.getMatricula());
            });
            Vendedor vendedor = modelMapper.map(dto, Vendedor.class);
            vendedor.setStatus(true);
            vendedor = vendedorRepository.save(vendedor);
            return modelMapper.map(vendedor, VendedorDTO.class);
    }

    public Vendedor getVendedorById(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new NotFound("Vendedor não encontrado"));
    }

    public List<VendedorDTO> getAll() {
        return vendedorRepository.findAll().stream()
                .map(vendedor -> modelMapper.map(vendedor, VendedorDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarVendedor(Long vendedorId) {

        Vendedor vendedorExists = getVendedorById(vendedorId);

        if (vendedorRepository.existsByVendas(vendedorExists.getId())) {
            throw new IllegalArgumentException("Não é possível excluir um vendedor que já realizou vendas.");
        }

        vendedorRepository.deleteById(vendedorId);
    }
}
