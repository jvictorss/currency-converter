package com.currencyconverter.currencyconverter.controller;

import com.currencyconverter.currencyconverter.service.ConversorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/cambio")
public class CambioController {
    @Autowired
    private ConversorService conversorService;

    @GetMapping(path = "/dolarhoje")
    public ResponseEntity<Double> consultarDolarHoje() throws IOException, InterruptedException {

        return ResponseEntity.ok(conversorService.getCotacaoDolar());
    }

    @GetMapping(path = "converter/reais-dolar/{valorReais}")
    public ResponseEntity<Double> converterReaisParaDolar(@PathVariable Double valorReais) throws IOException, InterruptedException {

        return ResponseEntity.ok(conversorService.converterReaisParaDolar(valorReais));
    }

    @GetMapping(path = "converter/dolar-reais/{valorDolar}")
    public ResponseEntity<Double> converterDolarParaReais(@PathVariable Double valorDolar) throws IOException, InterruptedException {

        return ResponseEntity.ok(conversorService.converterDolarParaReais(valorDolar));
    }
}
