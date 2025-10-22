package com.springdemo.API1.Services;


import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    public Double soma(Double a, Double b){
        return a+b;
    }

    public Double subtracao(Double a, Double b){
        return a-b;
    }

    public Double divisao(Double a, Double b){
        return a/b;
    }

    public Double multiplicacao(Double a, Double b){
        return a*b;
    }
}
