package com.dayswideawake.webrobot.lookupdefinition;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConversionServiceFactory;

import com.dayswideawake.webrobot.lookupdefinition.support.ConverterCollectorConversionServiceFactoryBean;

@SpringBootApplication
public class Application {
    
//    @Bean(name="conversionService")
//    public ConverterCollectorConversionServiceFactoryBean conversionService(){
//        return new ConverterCollectorConversionServiceFactoryBean();
//    }
    
    @Bean(name="conversionService")
    public ConversionServiceFactoryBean conversionService(){
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(new HashSet<>());
        return factory;
    }
//    
//    private Set<Converter<?, ?>> getConverters(){
//        HashSet<Converter<?, ?>> converters = new HashSet<>();
//        converters.add(e);
//        return converters;
//    }
    
//    @Bean(name="converterRegistrar")
//    public BeanPostProcessor converterRegistrar(){
//        return new ConverterRegistrarBeanPostProcessor();
//    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
