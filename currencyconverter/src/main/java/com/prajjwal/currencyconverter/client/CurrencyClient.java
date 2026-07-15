package com.prajjwal.currencyconverter.client;


//Call API → Receive Response → Return DTO

import com.prajjwal.currencyconverter.dto.CurrencyApiDTO;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component //"Create an object of this class and manage it."
public class CurrencyClient {
    private final RestClient restClient;
    //comes from RestClient

    @Value("${currency.api.key}")
    private String apiKey;

    //Constructor Injection
    public CurrencyClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public CurrencyApiDTO getExchangeRate(String fromCurrency, String toCurrency) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("apikey", apiKey)
                        .queryParam("base_currency", fromCurrency)
                        .queryParam("currencies", toCurrency)
                        .build())
                .retrieve()
                .body(CurrencyApiDTO.class);

    }
}