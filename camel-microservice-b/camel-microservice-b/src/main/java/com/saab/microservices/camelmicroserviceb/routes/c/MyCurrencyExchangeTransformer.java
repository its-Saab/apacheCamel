package com.saab.microservices.camelmicroserviceb.routes.c;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MyCurrencyExchangeTransformer {

    public CurrencyExchange transformConversionMultiple(CurrencyExchange currencyExchange) {
       currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
        return currencyExchange;
    }
}
