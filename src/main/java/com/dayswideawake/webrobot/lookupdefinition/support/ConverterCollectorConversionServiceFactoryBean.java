package com.dayswideawake.webrobot.lookupdefinition.support;

import java.util.Set;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

public class ConverterCollectorConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Autowired
    private Set<Converter> converters;
    private ConversionService conversionService;
    
//    @Autowired
//    public ConverterCollectorConversionServiceFactoryBean(Set<Converter<?, ?>> converters) {
//        super();
//        this.converters = converters;
//    }
    
    public ConverterCollectorConversionServiceFactoryBean() {
        super();
        System.out.println("### construct");
    }
    

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("### after properties set");
        this.conversionService = new DefaultConversionService();
        ConversionServiceFactory.registerConverters(this.converters, (ConverterRegistry) this.conversionService);
    }

    

    @Override
    public ConversionService getObject() throws Exception {
        return this.conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return GenericConversionService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
