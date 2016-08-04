package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionResponse;

@RestController
@RequestMapping(path = "/lookup-definitions", method = RequestMethod.POST)
public class AddLookupDefinitionPostController {

    private LookupDefinitionService lookupDefinitionService;
    private ConversionService conversionService;

    @Autowired
    public AddLookupDefinitionPostController(LookupDefinitionService lookupDefinitionService) {
        super();
        this.lookupDefinitionService = lookupDefinitionService;
    }

    @RequestMapping
    public AddLookupDefinitionResponse addLookupDefinition(AddLookupDefinitionRequest request) {
        LookupDefinition lookupDefinition = conversionService.convert(request, LookupDefinition.class);
        lookupDefinition = lookupDefinitionService.addLookupDefinition(lookupDefinition);
        return conversionService.convert(lookupDefinition, AddLookupDefinitionResponse.class);
    }

}
