package com.dayswideawake.webrobot.lookupdefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.stereotype.Component;

@Component
public class ConverterRegistrarBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ConversionService conversionService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Converter && conversionService instanceof ConverterRegistry){
            ((ConverterRegistry)conversionService).addConverter((Converter<?, ?>)bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
    
}
