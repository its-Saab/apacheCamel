package com.saab.microservices.camelmicroservicea.routes.advancedFiles;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AdvancedFileRouter2 extends RouteBuilder {

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
                .to("direct://log-file-values")
                .to("file: files/output");

        // Reusable route to be redirected to for logging files values
        from("direct://log-file-values")
                .log("${messageHistory} ${headers.CamelFileName}")
                .log("${file:name} ${file:name.ext} ${file:name.noext} ${file:onlyname}")
                .log("${file:onlyname.noext} ${file:parent} ${file:path} ${file:absolute}")
                .log("${file:size} ${file:modified}")
                .log("${routeId} ${camelId} ${body}");


    }
}
