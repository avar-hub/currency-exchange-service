package com.example.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircutBreakerController {

    @GetMapping("/circutBreaker")

//    We can use Circut breaker in place of Retry
//    @Retry(name = "sampleApi" , fallbackMethod = "fallBack")
    @CircuitBreaker(name = "sampleApi" , fallbackMethod = "fallBack")

    public String sampleApi() {
        log.info("Method k andar h apun");
        ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:0000/fake",String.class);
        return response.getBody();
    }

    public String fallBack(Exception exception) {
        return "ERROR BROTHER";
    }

    @GetMapping("/rateLimiter")
    @RateLimiter(name = "rateLim")
    public String rateLim() {
        log.info("RAte limiter mai h apun");
        return "Rate Limiter";
    }

    @GetMapping("/bulkHead")
    @Bulkhead(name = "bulkHead" , fallbackMethod = "fallBack")
    public String bulkHead() {
        log.info("Bulk Head h apun");
        return "Bulk Head";
    }
}
