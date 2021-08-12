package com.saab.microservices.camelmicroservicea.routes.advancedFiles;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
public class DeciderBean {

    Logger logger = LoggerFactory.getLogger(DeciderBean.class);

    public boolean isConditionMet(@Body String body, @Headers Map<String, String> headers,
                                  @Header("CamelFileName") String fileName,
                                  @ExchangeProperties Map<String, String> exchangeProperties) {
        logger.info("body: {} headers: {} fileName: {} exchangeProperties {}", body, headers, fileName, exchangeProperties);
        return true;
    }
}
