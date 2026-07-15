package com.prajjwal.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CurrencyConversionDTO {
    private String fromCurrency;

    private String toCurrency;

    private BigDecimal amount;

    private BigDecimal exchangeRate;

    private BigDecimal convertedAmount;
}
