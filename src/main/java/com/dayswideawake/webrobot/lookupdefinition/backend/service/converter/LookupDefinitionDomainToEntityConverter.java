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
public class LookupDefinitionDomainToEntityConverter implements Converter<LookupDefinition, LookupDefinitionEntity> {

    private ConversionService conversionService;

    @Autowired
    public LookupDefinitionDomainToEntityConverter(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

    @Override
    public LookupDefinitionEntity convert(LookupDefinition lookupDefinition) {
        SiteEntity siteEntity = conversionService.convert(lookupDefinition.getSite(), SiteEntity.class);
        SelectorEntity selectorEntity = conversionService.convert(lookupDefinition.getSelector(), SelectorEntity.class);
        Long interval = lookupDefinition.getIntervalInSeconds();
        LookupDefinitionEntity lookupDefinitionEntity =new LookupDefinitionEntity(siteEntity, selectorEntity, interval); 
        if(lookupDefinition.getAccountId() != null){
            lookupDefinitionEntity.setAccountId(lookupDefinition.getAccountId());
        }
        if(lookupDefinition.getLastLookupAt() != null){
            lookupDefinitionEntity.setLastLookupAt(lookupDefinition.getLastLookupAt().getTime());
        }
        return lookupDefinitionEntity;
    }

}
