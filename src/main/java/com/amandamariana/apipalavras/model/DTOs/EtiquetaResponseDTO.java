package com.amandamariana.apipalavras.model.DTOs;

import com.amandamariana.apipalavras.model.Etiqueta;

public record EtiquetaResponseDTO(Long id, String nome) {

    public static EtiquetaResponseDTO fromEntity(Etiqueta etiqueta)
    {
        return new EtiquetaResponseDTO(etiqueta.getId(), etiqueta.getNome());
    }
}
