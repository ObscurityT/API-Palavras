package com.amandamariana.apipalavras.services;

import com.amandamariana.apipalavras.model.DTOs.PalavraRequestDTO;
import com.amandamariana.apipalavras.model.DTOs.PalavraResponseDTO;
import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.model.Palavra;
import com.amandamariana.apipalavras.repository.EtiquetaRepository;
import com.amandamariana.apipalavras.repository.PalavraRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PalavraService {
    @Autowired
    PalavraRepository palavraRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Transactional
    public PalavraResponseDTO criarPalavra(PalavraRequestDTO data) {

        if (palavraRepository.existsByTermo(data.termo())) {
            throw new IllegalArgumentException("Já existe uma palavra com esse termo.");
        }

        Palavra novaPalavra = new Palavra();
        novaPalavra.setTermo(data.termo());

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

    public PalavraResponseDTO getPalavraId(Long id){
        Palavra palavraEncontrada = palavraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Palavra não encontrada com id: " + id));
        return PalavraResponseDTO.fromEntity(palavraEncontrada);
    }
}
