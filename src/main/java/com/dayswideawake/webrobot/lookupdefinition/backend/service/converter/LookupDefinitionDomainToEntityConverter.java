package com.dayswideawake.webrobot.lookupdefinition.backend.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SiteEntity;

@Component
public class LookupDefinitionDomainToEntityConverter implements Converter<LookupDefinition, LookupDefinitionEntity>{

    private ConversionService conversionService;
    
    
    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public LookupDefinitionEntity convert(LookupDefinition source) {
        SiteEntity siteEntity = conversionService.convert(source.getSite(), SiteEntity.class);
        SelectorEntity selectorEntity = conversionService.convert(source.getSelector(), SelectorEntity.class);
        Long interval = source.getIntervalInSeconds();
        Long lastLookupAt = source.getLastLookupAt().getTime();
        return new LookupDefinitionEntity(siteEntity, selectorEntity, interval, lastLookupAt);
    }

    
}
