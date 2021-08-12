package com.saab.microservices.camelmicroservicea.routes.advancedFiles;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvancedFileRouter3 extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;


    @Override
    public void configure() throws Exception {
        from("file: files/input")
                .routeId("Files-Input-Router")
                .transform().body(String.class)
                .choice()
                     .when(method(deciderBean, "isConditionMet"))
                         .log("XML File")
                     .when(simple("${body} contains 'USD'"))
                         .log("Contains USD")
                     .when(simple("${file:ext} == 'txt'"))
                         .log("txt File")
                     .otherwise()
                         .log("Not an XML File ")
                .end()
                .to("file: files/output");



    }
}
