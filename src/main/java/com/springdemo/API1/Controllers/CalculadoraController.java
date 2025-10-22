package com.springdemo.API1.Controllers;

import com.springdemo.API1.Services.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    @Autowired
    private CalculadoraService calculadoraService;

    @GetMapping("/soma/{a}/{b}")
    public Double soma(@PathVariable("a") Double a, @PathVariable("b") Double b ){
        return calculadoraService.soma(a,b);
    }

    @GetMapping("/subtracao/{a}/{b}")
    public Double subtracao(@PathVariable("a") Double a, @PathVariable("b") Double b ){
        return calculadoraService.subtracao(a,b);
    }

    @GetMapping("/divisao/{a}/{b}")
    public Double divisao(@PathVariable("a") Double a, @PathVariable("b") Double b ){
        return calculadoraService.divisao(a,b);
    }

    @GetMapping("/multiplicacao/{a}/{b}")
    public Double multiplicacao(@PathVariable("a") Double a, @PathVariable("b") Double b ){
        return calculadoraService.multiplicacao(a,b);
    }
}
