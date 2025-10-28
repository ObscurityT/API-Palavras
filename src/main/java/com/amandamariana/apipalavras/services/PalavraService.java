package com.amandamariana.apipalavras.services;

import com.amandamariana.apipalavras.model.DTOs.PalavraRequestDTO;
import com.amandamariana.apipalavras.model.DTOs.PalavraResponseDTO;
import com.amandamariana.apipalavras.model.Palavra;
import com.amandamariana.apipalavras.repository.PalavraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PalavraService {
    @Autowired
    PalavraRepository palavraRepository;

    public PalavraResponseDTO createPalavra(PalavraRequestDTO data) {
        Palavra novaPalavra = new Palavra();
        BeanUtils.copyProperties(data, novaPalavra);
        Palavra saved = palavraRepository.save(novaPalavra);
        return PalavraResponseDTO.fromEntity(saved);
    }

    public PalavraResponseDTO createPalavra(PalavraRequestDTO data) {
        Palavra novaPalavra = new Palavra();
        novaPalavra.setTermo(data.termo());

        // Se o usuário mandou etiquetas, busca e adiciona
        if (data.etiquetas() != null && !data.etiquetas().isEmpty()) {
            Set<Etiqueta> etiquetas = new HashSet<>(etiquetaRepository.findAllById(data.etiquetas()));
            novaPalavra.setEtiquetas(etiquetas);
        }

        Palavra saved = palavraRepository.save(novaPalavra);
        return PalavraResponseDTO.fromEntity(saved);
    }


    public List<PalavraResponseDTO> getTodasPalavras(){
        List<Palavra> palavras = palavraRepository.findAll();
        return palavras.stream()
                .map(PalavraResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
