package com.amandamariana.apipalavras.model.DTOs;

import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.model.Palavra;

import java.util.Set;
import java.util.stream.Collectors;

public record PalavraResponseDTO(
        Long id,
        String termo,
        Set<String> etiquetas
) {
    public static PalavraResponseDTO fromEntity(Palavra palavra) {
        Set<String> nomesEtiquetas = palavra.getEtiquetas().stream()
                .map(Etiqueta::getNome)
                .collect(Collectors.toSet());

        return new PalavraResponseDTO(palavra.getId(), palavra.getTermo(), nomesEtiquetas);
    }
}


