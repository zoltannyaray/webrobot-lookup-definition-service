package com.dayswideawake.webrobot.lookupdefinition.frontend.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionRequest;

public class AddLookupDefinitionRequestToDomainConverter implements Converter<AddLookupDefinitionRequest, LookupDefinition>{

    private ConversionService conversionService;
    
    @Autowired
    public AddLookupDefinitionRequestToDomainConverter(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

    @Override
    public LookupDefinition convert(AddLookupDefinitionRequest addLookupDefinitionRequest) {
        Site site = conversionService.convert(addLookupDefinitionRequest.getSite(), Site.class);
        Selector selector = conversionService.convert(addLookupDefinitionRequest.getSelector(), Selector.class);
        Long intervalInSeconds = addLookupDefinitionRequest.getIntervalInSeconds();
        LookupDefinition result = new LookupDefinition(site, selector, intervalInSeconds);
        if(addLookupDefinitionRequest.getAccountId() != null){
            result.setAccountId(addLookupDefinitionRequest.getAccountId());
        }
        return result;
    }

}
