package com.saab.microservices.camelmicroservicea.routes.d;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqJsonSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file: files/json")
                .log("${body}")
                .to("activemq:my-activemq-queue");
    }
}
