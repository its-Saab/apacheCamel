package com.saab.microservices.camelmicroservicea.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RouteSlipPatternRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        String splitConfig = "direct:endpoint1,direct:endpoint2,direct:endpoint3";

        from("timer:routingSplit?period=10000")
                .transform().constant("Hardcoded Message")
                        .routingSlip(simple(splitConfig));

        from("direct:endpoint1")
                .to("log:direct-endpoint1");

        from("direct:endpoint2")
                .to("log:direct-endpoint2");

        from("direct:endpoint3")
                .to("log:direct-endpoint3");
    }
}
