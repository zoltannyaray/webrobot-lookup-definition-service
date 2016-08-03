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
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public LookupDefinition convert(LookupDefinitionEntity lookupDefintionEntity) {
        Site site = conversionService.convert(lookupDefintionEntity.getSite(), Site.class);
        Selector selector = conversionService.convert(lookupDefintionEntity.getSelector(), Selector.class);
        Long intervalInSeconds = lookupDefintionEntity.getIntervalInSeconds();
        Date lastLookupAt = new Date(lookupDefintionEntity.getLastLookupAt());
        return new LookupDefinition(site, selector, intervalInSeconds, lastLookupAt);
    }

}
