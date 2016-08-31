package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.exception.LookupDefinitionNotFoundException;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.ViewLookupDefinitionResponse;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionViewDomainTransformer;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@RestController
public class ViewLookupDefinitionController {

    private LookupDefinitionService lookupDefinitionService;
    private LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer;

    @Autowired
    public ViewLookupDefinitionController(LookupDefinitionService lookupDefinitionService, LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer) {
        this.lookupDefinitionService = lookupDefinitionService;
        this.lookupDefinitionViewDomainTransformer = lookupDefinitionViewDomainTransformer;
    }

    @RequestMapping(path = LookupDefinitionUrls.BASE_URL + "/{id}", method = RequestMethod.GET)
    public ViewLookupDefinitionResponse view(@PathVariable Long id) {
        Optional<LookupDefinition> lookupDefinition = lookupDefinitionService.getLookupDefinitionById(id);
        if (!lookupDefinition.isPresent()) {
            throw new LookupDefinitionNotFoundException(id);
        }
        return lookupDefinitionViewDomainTransformer.domainToViewResponse(lookupDefinition.get());
    }

}
