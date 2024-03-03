package com.example.currencyexchangeservice.dao;

import com.example.currencyexchangeservice.dto.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange,Long> {

    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
