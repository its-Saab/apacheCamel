package com.saab.microservices.camelmicroservicea.patterns;


import com.saab.microservices.camelmicroservicea.ArrayListAggregationStrategy;
import com.saab.microservices.camelmicroservicea.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;


//@Component
public class ContentBasedPatternAggregateRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        //Content Based Routing - choice()
        //Aggregate Pattern:

        //To ensure no messages are lost
        errorHandler(deadLetterChannel("activemq:dead-letter-queue"));

        from("file: files/aggregate-json")
                .convertBodyTo(String.class)
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
                .completionSize(4)
                .completionTimeout(5000)
                .log("${body}")
                .to("activemq:split-activemq-queue");


    }
}