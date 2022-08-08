package com.example.HealFitNest.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    //As soon as the user enters any value as the data this event is going to trigger and if the entered value
    //is null then this triggers "constraint violation exception".
    @Bean
    public ValidatingMongoEventListener validationMongoEventListener(){
        return new ValidatingMongoEventListener(validator());
    }

    //Local factory bean is the implementation class for the listener and this validator will be given as the
    //parameter to the validating mongo event listener.
    @Bean
    public LocalValidatorFactoryBean validator(){
        return new LocalValidatorFactoryBean();
    }
}
