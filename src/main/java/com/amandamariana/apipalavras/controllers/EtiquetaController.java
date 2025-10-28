package com.amandamariana.apipalavras.controllers;

import com.amandamariana.apipalavras.model.Etiqueta;
import com.amandamariana.apipalavras.services.etiquetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class etiquetaController {

    private etiquetaService etiquetaService;

    @GetMapping
    public ResponseEntity<List<Etiqueta>> listar()
    {
        List<Etiqueta> etiquetas =  etiquetaService.listarTodas();
        return ResponseEntity.ok(etiquetas);
    }

    @PostMapping
    public ResponseEntity<Etiqueta> create(@RequestBody Etiqueta etiqueta) {
        Etiqueta novaEtiqueta = etiquetaService.salvar(etiqueta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEtiqueta);
    }

//    @GetMapping{"/{nome}"}
//    public ResponseEntity<Etiqueta>buscarPorPalavras(@PathVariable String nome)
//    {
//        return etiquetaService.buscarPorNome(nome).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build);
//    }

}
