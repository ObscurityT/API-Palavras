package com.amandamariana.apipalavras.model.DTOs;

import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.model.Palavra;

import java.util.Set;
import java.util.stream.Collectors;

public record EtiquetaComPalavrasResponseDTO(Long id,
                                             String nome,
                                             Set<String> palavras) {

    public static EtiquetaComPalavrasResponseDTO fromEntity(Etiqueta etiqueta) {
        Set<String> nomesPalavras = etiqueta.getPalavras().stream()
                .map(Palavra::getTermo)
                .collect(Collectors.toSet());

        return new EtiquetaComPalavrasResponseDTO(etiqueta.getId(), etiqueta.getNome(), nomesPalavras);
    }
}
