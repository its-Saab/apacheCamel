package com.saab.microservices.camelmicroservicea.routes.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    //best practice to autowire the bean so if the name changes it wont compile
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer?period=10000")
                .log("${body}")
                .transform().constant("My Constant Message")
                .log("${body}")
               // .transform().constant("Time now is " + LocalDateTime.now())
              //  .bean("getCurrentTimeBean") //must start with lowercase
                .process(new SimpleLoggingProcessor())
                .bean(loggingComponent)
                .bean(getCurrentTimeBean, "getCurrentTime")
                .to("log:first-timer");
    }
}

@Component
class GetCurrentTimeBean{
    public String getCurrentTime(){
        return "Time now is " + LocalDateTime.now();
    }

    public String getCurrentTime1(){
        return "Time now is " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent{
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message){
        logger.info("SimpleLoggingProcessingComponent {}", message);
    }

}

class SimpleLoggingProcessor implements Processor{
    Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }
}