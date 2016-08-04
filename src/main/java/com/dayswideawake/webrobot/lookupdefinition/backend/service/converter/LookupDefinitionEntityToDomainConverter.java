package com.dayswideawake.webrobot.lookupdefinition.backend.service.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;

@Component
public class LookupDefinitionEntityToDomainConverter implements Converter<LookupDefinitionEntity, LookupDefinition> {

    private ConversionService conversionService;
    
    @Autowired
    public LookupDefinitionEntityToDomainConverter(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }


    @Override
    public LookupDefinition convert(LookupDefinitionEntity lookupDefinitionEntity) {
        Site site = conversionService.convert(lookupDefinitionEntity.getSite(), Site.class);
        Selector selector = conversionService.convert(lookupDefinitionEntity.getSelector(), Selector.class);
        Long intervalInSeconds = lookupDefinitionEntity.getIntervalInSeconds();
        LookupDefinition lookupDefinition = new LookupDefinition(site, selector, intervalInSeconds);
        if(lookupDefinitionEntity.getAccountId() != null){
            lookupDefinition.setAccountId(lookupDefinitionEntity.getAccountId());
        }
        if(lookupDefinitionEntity.getLastLookupAt() != null){
            lookupDefinition.setLastLookupAt(new Date(lookupDefinitionEntity.getLastLookupAt()));
        }
        return lookupDefinition; 
    }

}
