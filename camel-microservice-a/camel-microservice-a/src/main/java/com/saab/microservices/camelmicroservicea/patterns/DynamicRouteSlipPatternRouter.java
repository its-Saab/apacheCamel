package com.saab.microservices.camelmicroservicea.patterns;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DynamicRouteSlipPatternRouter extends RouteBuilder {

    @Autowired
    DynamicRouterBean dynamicRouterBean;

    @Override
    public void configure() throws Exception {

        from("timer:routingSplit?period={{timePeriod}}")
                .transform().constant("Hardcoded Message")
                .dynamicRouter(method(dynamicRouterBean));

        from("direct:endpoint1")
                //.wireTap("activemq:wired-activemq-queue") add the same following logging to the given endpoint
                .to("{{log-endpoint}}");

        from("direct:endpoint2")
                .to("log:direct-endpoint2");

        from("direct:endpoint3")
                .to("log:direct-endpoint3");
    }
}

@Component
class DynamicRouterBean {
    Logger logger = LoggerFactory.getLogger(DynamicRouterBean.class);
    int invocations;

    public String decideTheNextEndpoint(
            @ExchangeProperties Map<String, String> properties,
            @Headers Map<String, String> headers,
            @Body String body
    ) {
        logger.info("{} {} {}", body, headers, properties);
        invocations++;
        if (invocations % 3 == 0) {
            return "direct:endpoint1";
        } else if (invocations % 3 == 1) {
            return "direct:endpoint2,direct:endpoint3";
        } else {
            return null;
        }

    }


}