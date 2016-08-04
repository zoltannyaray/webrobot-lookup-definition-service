package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionPostRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionPostResponse;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionViewDomainTransformer;

@RestController
@RequestMapping(path = "/lookup-definitions", method = RequestMethod.POST)
public class AddLookupDefinitionPostController {

    private LookupDefinitionService lookupDefinitionService;
    private LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer;

    @Autowired
    public AddLookupDefinitionPostController(LookupDefinitionService lookupDefinitionService, LookupDefinitionViewDomainTransformer lookupDefinitionPostRequestDomainTransformer) {
        super();
        this.lookupDefinitionService = lookupDefinitionService;
        this.lookupDefinitionViewDomainTransformer = lookupDefinitionPostRequestDomainTransformer;
    }

    @RequestMapping
    public LookupDefinitionPostResponse addLookupDefinition(LookupDefinitionPostRequest request) {
        LookupDefinition lookupDefinition = lookupDefinitionViewDomainTransformer.postRequestToDomain(request);
        lookupDefinition = lookupDefinitionService.addLookupDefinition(lookupDefinition);
        return lookupDefinitionViewDomainTransformer.domainToPostResponse(lookupDefinition);
    }

}
