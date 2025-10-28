package com.amandamariana.apipalavras.controllers;

import com.amandamariana.apipalavras.model.DTOs.PalavraRequestDTO;
import com.amandamariana.apipalavras.model.DTOs.PalavraResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amandamariana.apipalavras.services.PalavraService;


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

    @PostMapping("/criar")
    public ResponseEntity<?> criarPalavra(@RequestBody @Valid PalavraRequestDTO data) {
        try {
            PalavraResponseDTO created = palavraService.criarPalavra(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar palavra: " + e.getMessage());
        }
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity updatePalavra(@PathVariable(value="id") Long id, @RequestBody @Valid PalavraRequestDTO data) {
//        if(id.equals(data.id())){
//            Optional<Palavra> optionalPalavra = palavraRepository.findById(id);
//            if (optionalPalavra.isPresent()) {
//                var productUpdate = optionalPalavra.get();
//                BeanUtils.copyProperties(data, productUpdate);
//                return ResponseEntity.status(HttpStatus.OK).body(palavraRepository.save(productUpdate));
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Palavra de id %s nao encontrado!".formatted(id));
//            }
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O Id do parâmetro não é o mesmo do body");
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity deletePalavra(@PathVariable(value = "id")Long id){
//        Optional<Palavra> optionalPalavra = palavraRepository.findById(id);
//        if (optionalPalavra.isPresent()) {
//            palavraRepository.delete( optionalPalavra.get());
//            return ResponseEntity.status(HttpStatus.OK).body("Palavra deletada corretamente");
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Palavra de id %s nao encontrado!".formatted(id));
//    }
}
