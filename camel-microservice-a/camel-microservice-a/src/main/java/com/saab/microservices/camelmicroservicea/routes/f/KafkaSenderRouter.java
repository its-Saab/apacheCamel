package com.saab.microservices.camelmicroservicea.routes.f;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file: files/json")
                .to("kafka:myKafkaTopic");
    }
}
