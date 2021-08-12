package com.saab.microservices.camelmicroservicea.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ContentBasedPatterRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //Content Based Routing - choice()
        from("file: files/csv")
                .unmarshal().csv()
                .split(body())
                .log("${body}")
                .to("activemq:split-activemq-queue");
    }
}
