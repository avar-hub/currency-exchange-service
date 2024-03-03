package com.example.currencyexchangeservice.controller;

import com.example.currencyexchangeservice.dao.CurrencyExchangeRepo;
import com.example.currencyexchangeservice.dto.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepo repo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getCurrencyExchange(@PathVariable String from ,
                                                @PathVariable String to){
       String port= environment.getProperty("local.server.port");
        //return new CurrencyExchange(1000L,from,to, BigDecimal.valueOf(80),port);

        return repo.findByFromAndTo(from,to).orElseThrow(()-> new RuntimeException("Not found"));
    }

}
