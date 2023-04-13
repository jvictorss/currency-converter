package com.currencyconverter.currencyconverter.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConversorService {

    private static final String URL_COTACAO_DOLAR = "https://economia.awesomeapi.com.br/last/USD-BRL";

    public double getCotacaoDolar() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_COTACAO_DOLAR))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        DocumentContext json = JsonPath.parse(response.body());
        Double bid = Double.valueOf(json.read("$.USDBRL.bid"));

        return bid;
    }

    public double converterReaisParaDolar(double valor) throws IOException, InterruptedException {
        Double dolarHoje = getCotacaoDolar();
        return valor / dolarHoje;
    }

    public double converterDolarParaReais(double valor) throws IOException, InterruptedException {
        Double dolarHoje = getCotacaoDolar();
        return valor * dolarHoje;
    }
}
