package com.saab.microservices.camelmicroservicea.routes.gRest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestApiConsumerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost").port(8000);

        from("timer:rest-api-consumer?period=10_000")
                .setHeader("from", () -> "EUR")
                .setHeader("to", () -> "USD")
                .log("${body}")
                .to("rest:get:/currency-exchange/from/{from}/to/{to}")
                .log("${body}");
    }
}
