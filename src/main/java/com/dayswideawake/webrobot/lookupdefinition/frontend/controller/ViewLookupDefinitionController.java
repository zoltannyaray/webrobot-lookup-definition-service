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
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionResource;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionResourceAssembler;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@RestController
@RequestMapping(path = LookupDefinitionUrls.BASE_URL)
public class ViewLookupDefinitionController {

    private LookupDefinitionService lookupDefinitionService;
    private LookupDefinitionResourceAssembler lookupDefinitionResourceAssembler;

    @Autowired
    public ViewLookupDefinitionController(LookupDefinitionService lookupDefinitionService, LookupDefinitionResourceAssembler lookupDefinitionResourceAssembler) {
		this.lookupDefinitionService = lookupDefinitionService;
		this.lookupDefinitionResourceAssembler = lookupDefinitionResourceAssembler;
	}

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public LookupDefinitionResource handle(@PathVariable Long id) {
        Optional<LookupDefinition> lookupDefinition = lookupDefinitionService.getLookupDefinitionById(id);
        if (!lookupDefinition.isPresent()) {
            throw new LookupDefinitionNotFoundException(id);
        }
        return lookupDefinitionResourceAssembler.toResource(lookupDefinition.get());
    }

}
