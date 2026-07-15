package com.prajjwal.currencyconverter.service;

import com.prajjwal.currencyconverter.dto.CurrencyConversionDTO;

import java.math.BigDecimal;

public interface CurrencyService {
    CurrencyConversionDTO convertCurrency(
            String fromCurrency,
            String toCurrency,
            BigDecimal amount
    );
}
