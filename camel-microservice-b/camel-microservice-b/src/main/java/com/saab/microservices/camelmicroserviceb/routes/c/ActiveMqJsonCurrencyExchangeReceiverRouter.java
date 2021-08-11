package com.saab.microservices.camelmicroserviceb.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqJsonCurrencyExchangeReceiverRouter extends RouteBuilder {

    @Autowired
    private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
    @Autowired
    private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;
    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
                .log("${body}")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .bean(myCurrencyExchangeProcessor)
                .log("${body}")
                .bean(myCurrencyExchangeTransformer, "transformConversionMultiple")
                .log("${body}")
                .log("log:received-message-from-activemq-queue");
    }
}

