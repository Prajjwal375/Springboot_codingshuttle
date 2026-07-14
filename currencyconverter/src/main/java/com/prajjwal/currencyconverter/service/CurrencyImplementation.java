package com.prajjwal.currencyconverter.service;


import com.prajjwal.currencyconverter.client.CurrencyClient;
import com.prajjwal.currencyconverter.dto.CurrencyApiDTO;
import com.prajjwal.currencyconverter.dto.CurrencyConversionDTO;
import com.prajjwal.currencyconverter.entity.CurrencyEntity;
import com.prajjwal.currencyconverter.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;
import lombok.ToString;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CurrencyImplementation implements CurrencyService {
    private static final Logger logger =
            LoggerFactory.getLogger(CurrencyImplementation.class);

    private final CurrencyClient currencyClient;
    private final CurrencyRepository currencyRepository;

    public CurrencyImplementation(CurrencyClient currencyClient, CurrencyRepository currencyRepository) {
        this.currencyClient = currencyClient;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CurrencyConversionDTO convertCurrency(String fromCurrency,
                                                 String toCurrency,
                                                 BigDecimal amount){
        logger.info("Received request: from={}, to={}, amount={}",
                fromCurrency, toCurrency, amount);

        CurrencyApiDTO response = currencyClient.getExchangeRate(fromCurrency, toCurrency);
        logger.info("API Response: {}", response);

        BigDecimal exchangeRate = response.getData().get(toCurrency);
        logger.info("Exchange Rate: {}", exchangeRate);

        BigDecimal convertedAmount = amount.multiply(exchangeRate);
        CurrencyEntity currencyEntity = new CurrencyEntity();


        currencyEntity.setFromCurrency(fromCurrency);
        currencyEntity.setToCurrency(toCurrency);
        currencyEntity.setAmount(amount);
        currencyEntity.setExchangeRate(exchangeRate);
        currencyEntity.setConvertedAmount(convertedAmount);
        logger.info("Entity Before Save: {}", currencyEntity);


        currencyRepository.save(currencyEntity);
        logger.info("Entity After Save: {}", currencyEntity);

        return CurrencyConversionDTO.builder()
                .fromCurrency(fromCurrency)
                .toCurrency(toCurrency)
                .amount(amount)
                .exchangeRate(exchangeRate)
                .convertedAmount(convertedAmount)
                .build();
    }
}
