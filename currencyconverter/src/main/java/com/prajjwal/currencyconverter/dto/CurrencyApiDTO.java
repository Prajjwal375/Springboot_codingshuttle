package com.prajjwal.currencyconverter.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyApiDTO {
    private Map<String, BigDecimal> data;
}
