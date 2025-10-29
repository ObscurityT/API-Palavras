package com.amandamariana.apipalavras.controllers;

import com.amandamariana.apipalavras.model.DTOs.EtiquetaResponseDTO;
import com.amandamariana.apipalavras.model.DTOs.PalavraRequestDTO;
import com.amandamariana.apipalavras.model.DTOs.PalavraResponseDTO;
import com.amandamariana.apipalavras.model.Etiqueta;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amandamariana.apipalavras.services.PalavraService;

import java.util.List;


@RestController
@RequestMapping("/palavra")
public class PalavraController {
    @Autowired
    PalavraService palavraService;

    @GetMapping("/todas")
    public ResponseEntity<?> getTodasPalavras() {
        try {
            var allPalavras = palavraService.getTodasPalavras();
            return ResponseEntity.status(HttpStatus.OK).body(allPalavras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar palavra: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPalavraById(@PathVariable(value = "id") Long id) {
        try {
            PalavraResponseDTO response = palavraService.getPalavraId(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Palavra não encontrado");
        }
    }

    @GetMapping("/{termo}/etiquetas")
    public ResponseEntity<List<EtiquetaResponseDTO>> getPalavrasPorEtiquetas(@PathVariable String termo)
    {
        List<Etiqueta> etiquetas = palavraService.buscarEtiquetasPorPalavra(termo);
        List<EtiquetaResponseDTO> response = etiquetas.stream()
                .map(e-> new EtiquetaResponseDTO(e.getId(), e.getNome())).toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarPalavra(@RequestBody @Valid PalavraRequestDTO data) {
        try {
            PalavraResponseDTO created = palavraService.criarPalavra(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar palavra: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updatePalavra(@PathVariable(value = "id") Long id, @RequestBody @Valid PalavraRequestDTO data) {
        try {
            PalavraResponseDTO atualizada = palavraService.atualizaPalavra(id, data);
            return ResponseEntity.status(HttpStatus.OK).body(atualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar palavra: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletePalavra(@PathVariable(value = "id") Long id) {
        try {
            palavraService.deletarPalavra(id);
            return ResponseEntity.status((HttpStatus.OK)).body("Palavra deletada com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar palavra: " + e.getMessage());
        }
    }
}
