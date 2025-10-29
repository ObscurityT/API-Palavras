package com.amandamariana.apipalavras.controllers;

import com.amandamariana.apipalavras.model.DTOs.EtiquetaRequestDTO;
import com.amandamariana.apipalavras.model.DTOs.EtiquetaResponseDTO;
import com.amandamariana.apipalavras.model.DTOs.PalavraResponseDTO;
import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.model.Palavra;
import com.amandamariana.apipalavras.services.EtiquetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("api/v1/etiquetas")
@RestController
public class EtiquetaController {

    private final EtiquetaService etiquetaService;

    @GetMapping
    public ResponseEntity<List<EtiquetaResponseDTO>> listar() {
        List<EtiquetaResponseDTO> etiquetas = etiquetaService.listarTodas()
                        .stream()
                                .map(e-> new EtiquetaResponseDTO(e.getId(), e.getNome())).toList();
        return ResponseEntity.ok(etiquetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtiquetaResponseDTO> getEtiquetaPorId(@PathVariable Long id) {
        Etiqueta etiqueta = etiquetaService.buscarPorID(id);
        EtiquetaResponseDTO response = new EtiquetaResponseDTO(etiqueta.getId(), etiqueta.getNome());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{nome}/palavras")
    public ResponseEntity<List<PalavraResponseDTO>>getEtiquetaPorPalavras(@PathVariable String nome) {
        List<Palavra> palavras = etiquetaService.buscarPalavrasPorEtiqueta(nome);
        List<PalavraResponseDTO> response = palavras.stream()
                .map(p -> new PalavraResponseDTO(p.getId(), p.getTermo())).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EtiquetaResponseDTO> criar(@RequestBody EtiquetaRequestDTO etiquetaDto) {
        Etiqueta etiqueta = etiquetaService.salvar(etiquetaDto);
        EtiquetaResponseDTO response = new EtiquetaResponseDTO(etiqueta.getId(), etiqueta.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{nome}")
    public ResponseEntity<EtiquetaResponseDTO> atualizar(@PathVariable String nome, @RequestBody EtiquetaRequestDTO etiquetaDto)
    {
        Etiqueta updated = etiquetaService.atualizarPorNome(nome, etiquetaDto);
        EtiquetaResponseDTO response =  new EtiquetaResponseDTO(updated.getId(), updated.getNome());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{nome}")
    public ResponseEntity<Void> deletarPorNome(@PathVariable String nome)
    {
        etiquetaService.deletarPorNome(nome);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping{"/{nome}"}
//    public ResponseEntity<Etiqueta>buscarPorPalavras(@PathVariable String nome)
//    {
//        return etiquetaService.buscarPorNome(nome).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build);
//    }

}
