package com.saab.microservices.camelmicroservicea.routes.advancedFiles;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class AdvancedFileRouter1 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file: files/input")
                .routeId("Files-Input-Router")
                .transform().body(String.class)
                .choice()
                     .when(simple("${file:ext} ends with 'xml'"))
                         .log("XML File")
                     .when(simple("${body} contains 'USD'"))
                         .log("Contains USD")
                     .when(simple("${file:ext} == 'txt'"))
                         .log("txt File")
                     .otherwise()
                         .log("Not an XML File ")
                .end()
                .log("${messageHistory} ${headers.CamelFileName}")
                .to("file: files/output");
    }
}
