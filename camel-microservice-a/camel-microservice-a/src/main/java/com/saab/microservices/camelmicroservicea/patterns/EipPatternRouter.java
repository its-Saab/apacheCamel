package com.saab.microservices.camelmicroservicea.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class EipPatternRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:EipRouter?period=10000")
                .transform().constant("multicast sends copy of the message to all children")
                .multicast()
                .to("log:log1", "log:log2", "log:log3");
    }
}
