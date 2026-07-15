package com.prajjwal.currencyconverter.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.hibernate.annotations.Audited;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Entity
@Table(name = "currencyEntity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Audited
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 3)
    private String fromCurrency;

    @Column(nullable = false, length = 3)
    private String toCurrency;

    private BigDecimal amount;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;
}
