package com.saab.microservices.camelmicroserviceb.routes.csv;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CsvActiveMqReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("activemq:split-activemq-queue")
                .log("${body}")
                .to("log:received-message-from-split-activemq-queue");
    }
}
