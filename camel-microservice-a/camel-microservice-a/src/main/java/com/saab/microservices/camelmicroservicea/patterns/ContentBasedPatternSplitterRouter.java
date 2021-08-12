package com.saab.microservices.camelmicroservicea.patterns;

import org.apache.camel.Body;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

//@Component
public class ContentBasedPatternSplitterRouter extends RouteBuilder {

    @Autowired
    private SplitterBean splitter;

    @Override
    public void configure() throws Exception {
        //Content Based Routing - choice()
        //Split Pattern:
        //Method 1
//        from("file: files/csv")
//                .unmarshal().csv()
//                .split(body())
//                .log("${body}")
//                .to("activemq:split-activemq-queue");


        //Method 2
//        from("file: files/csv")
//                .convertBodyTo(String.class)
//                .split(body(), ",")
//                .log("${body}")
//                .to("activemq:split-activemq-queue");


        //Method 3
        from("file: files/csv")
                .convertBodyTo(String.class)
                .split(method(splitter))
                .log("${body}")
                .to("activemq:split-activemq-queue");


    }
}

@Component
class SplitterBean {

    public Stream<String> splitInput(@Body String body){
        return Arrays.stream(body.split(","));
    }
}