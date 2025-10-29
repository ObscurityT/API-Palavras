package com.amandamariana.apipalavras.model.DTOs;

import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.model.Palavra;

import java.util.Set;
import java.util.stream.Collectors;

public record EtiquetaComPalavrasResponseDTO(Long id,
                                             String nome,
                                             Set<PalavraResponseDTO> palavras) {

    public static EtiquetaComPalavrasResponseDTO fromEntity(Etiqueta etiqueta) {
        Set<PalavraResponseDTO> nomesPalavras = etiqueta.getPalavras().stream()
                .map(PalavraResponseDTO::fromEntity)
                .collect(Collectors.toSet());

        return new EtiquetaComPalavrasResponseDTO(etiqueta.getId(), etiqueta.getNome(), nomesPalavras);
    }
}
