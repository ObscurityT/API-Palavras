package com.amandamariana.apipalavras.model.DTOs;

import com.amandamariana.apipalavras.model.Palavra;

import java.util.Set;
import java.util.stream.Collectors;

public record PalavraComEtiquetasResponseDTO(Long id, String termo, Set<EtiquetaResponseDTO> etiquetas) {

    public static PalavraComEtiquetasResponseDTO fromEntity(Palavra palavra)
    {
        Set<EtiquetaResponseDTO> etiquetasDTO = palavra.getEtiquetas().stream()
                .map(EtiquetaResponseDTO::fromEntity).collect(Collectors.toSet());
        return new PalavraComEtiquetasResponseDTO(palavra.getId(), palavra.getTermo(), etiquetasDTO);
    }

}
