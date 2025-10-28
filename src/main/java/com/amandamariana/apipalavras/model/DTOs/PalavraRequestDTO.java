package com.amandamariana.apipalavras.model.DTOs;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

public record PalavraRequestDTO(@NotBlank String termo, Set<Long> etiquetas){


}
