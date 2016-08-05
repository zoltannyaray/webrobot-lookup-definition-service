package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionDetails;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionViewDomainTransformer;

@RestController
@RequestMapping(path="/lookup-definitions/{id}", method=RequestMethod.GET)
public class ViewLookupDefinitionController {

    private LookupDefinitionService lookupDefinitionService;
    private LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer;
    
    @Autowired
    public ViewLookupDefinitionController(LookupDefinitionService lookupDefinitionService, LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer) {
        super();
        this.lookupDefinitionService = lookupDefinitionService;
        this.lookupDefinitionViewDomainTransformer = lookupDefinitionViewDomainTransformer;
    }

    @RequestMapping
    public LookupDefinitionDetails view(@PathVariable Long id){
        LookupDefinition lookupDefinition = lookupDefinitionService.getLookupDefinitionById(id);
        if(lookupDefinition != null){
            return lookupDefinitionViewDomainTransformer.domainToDetails(lookupDefinition); 
        }
        else {
            return null;
        }
    }

    
    
}
