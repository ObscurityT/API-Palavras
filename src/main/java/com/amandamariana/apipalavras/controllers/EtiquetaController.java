package com.amandamariana.apipalavras.controllers;

import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.services.EtiquetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/etiquetas")
@RestController
public class EtiquetaController {

    private EtiquetaService etiquetaService;

    @GetMapping
    public ResponseEntity<List<Etiqueta>> listar() {
        List<Etiqueta> etiquetas = etiquetaService.listarTodas();
        return ResponseEntity.ok(etiquetas);
    }

    @PostMapping
    public ResponseEntity<Etiqueta> create(@RequestBody Etiqueta etiqueta) {
        Etiqueta novaEtiqueta = etiquetaService.salvar(etiqueta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEtiqueta);
    }

    @PutMapping("/nome")
    public ResponseEntity<Etiqueta> atualizar(@PathVariable String nome, @RequestBody Etiqueta etiqueta)
    {
        Etiqueta updated = etiquetaService.atualizarPorNome(nome, etiqueta);
        return ResponseEntity.ok(updated);
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
