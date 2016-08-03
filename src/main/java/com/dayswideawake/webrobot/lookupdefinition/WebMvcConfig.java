package com.dayswideawake.webrobot.lookupdefinition;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dayswideawake.webrobot.lookupdefinition.backend.service.converter.LookupDefinitionEntityToDomainConverter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LookupDefinitionEntityToDomainConverter());
    }

    
    
}
