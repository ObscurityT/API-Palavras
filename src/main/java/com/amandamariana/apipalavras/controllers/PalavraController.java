package com.amandamariana.apipalavras.controllers;

import com.amandamariana.apipalavras.model.DTOs.PalavraRequestDTO;
import com.amandamariana.apipalavras.repository.PalavraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.amandamariana.apipalavras.services.PalavraService;
import com.amandamariana.apipalavras.model.Palavra;

import java.util.Optional;


@Controller
@RequestMapping("/palavra")
public class PalavraController {
    @Autowired
    PalavraService palavraService;

    @Autowired
    PalavraRepository palavraRepository;

    @GetMapping("/all")
    public ResponseEntity getAllPalavras(){
        var allPalavras = palavraRepository.findAll();
        return ResponseEntity.ok(allPalavras);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPalavraById(@PathVariable(value="id")Long id){
        Optional<Palavra> productF = palavraRepository.findById(id);
        if(productF.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(productF.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Palavra não encontrado");
    }

    @PostMapping("/create")
    public ResponseEntity createPalavra(@RequestBody @Valid PalavraRequestDTO data){
        var newPalavra = new Palavra();

        BeanUtils.copyProperties(data, newPalavra); //converte o DTO para product
        return ResponseEntity.status(HttpStatus.CREATED).body(palavraRepository.save(newPalavra));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePalavra(@PathVariable(value="id") Long id, @RequestBody @Valid PalavraRequestDTO data) {
        if(id.equals(data.getId())){
            Optional<Palavra> optionalPalavra = palavraRepository.findById(id);
            if (optionalPalavra.isPresent()) {
                var productUpdate = optionalPalavra.get();
                BeanUtils.copyProperties(data, productUpdate);
                return ResponseEntity.status(HttpStatus.OK).body(palavraRepository.save(productUpdate));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Palavra de id %s nao encontrado!".formatted(id));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O Id do parâmetro não é o mesmo do body");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePalavra(@PathVariable(value = "id")Long id){
        Optional<Palavra> optionalPalavra = palavraRepository.findById(id);
        if (optionalPalavra.isPresent()) {
            palavraRepository.delete( optionalPalavra.get());
            return ResponseEntity.status(HttpStatus.OK).body("Palavra deletada corretamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Palavra de id %s nao encontrado!".formatted(id));
    }
}
