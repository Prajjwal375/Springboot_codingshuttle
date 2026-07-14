package com.prajjwal.currencyconverter.repositories;

import com.prajjwal.currencyconverter.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long > {
}
