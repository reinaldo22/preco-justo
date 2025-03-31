package com.patos.vendas.service;

import com.patos.vendas.exception.NotFound;
import com.patos.vendas.model.Pato;
import com.patos.vendas.model.dto.ClienteDTO;
import com.patos.vendas.model.dto.PatoDTO;
import com.patos.vendas.repositorie.PatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatoService {

    @Autowired
    private PatoRepository patoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PatoDTO create(PatoDTO dto) {
        Pato pato = new Pato();

        pato.setNome(dto.getNome());

        if (dto.getMae() != null) {
            Pato mae = patoRepository.findById(dto.getMae())
                    .orElseThrow(() -> new NotFound("Mãe não encontrada com ID: " + dto.getMae()));

            pato.setMae(mae);

            Long numeroFilhos = countFilhos(pato);
            BigDecimal grauFamiliar = mae.getProfundidade();
            BigDecimal novaProfundidade = grauFamiliar.add(BigDecimal.valueOf(0.01 * (numeroFilhos + 1)));

            pato.setProfundidade(novaProfundidade);
        } else {
            pato.setProfundidade(BigDecimal.valueOf(1));
        }

        pato = patoRepository.save(pato);
        return new PatoDTO(pato);
    }


    public Long countFilhos(Pato pato) {
        if (pato.getMae() == null) {
            return 0L;
        }
        return patoRepository.countByMaeId(pato.getMae().getId());
    }



    public List<Pato> findAllById(List<Long> patosId) {
        List<Pato> patos = patoRepository.findAllById(patosId);

        if (patos.size() != patosId.size()) {
            throw new NotFound("Um ou mais patos não foram encontrados!");
        }

        return patos;
    }

    public Pato update(Long id, Pato dto) {
        Pato pato = patoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Cliente não encontrado"));
        modelMapper.map(dto, pato);
        pato = patoRepository.save(pato);
        return modelMapper.map(pato, Pato.class);
    }

    public List<PatoDTO> getAll() {

        return patoRepository.findAll().stream()
                .map(patos -> modelMapper.map(patos, PatoDTO.class))
                .collect(Collectors.toList());
    }

}
