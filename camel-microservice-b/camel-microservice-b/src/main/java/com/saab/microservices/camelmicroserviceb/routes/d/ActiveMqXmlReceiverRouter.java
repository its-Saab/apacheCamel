package com.saab.microservices.camelmicroserviceb.routes.d;

import com.saab.microservices.camelmicroserviceb.routes.c.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqXmlReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-xml-queue")
                .log("${body}")
                .unmarshal()
                .jacksonxml(CurrencyExchange.class)
                .log("log:received-message-from-my-activemq-xml-queue");
    }
}
