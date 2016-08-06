package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionPostRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionViewDomainTransformer;

@RestController
@RequestMapping(path = "/lookup-definitions", method = RequestMethod.POST)
public class AddLookupDefinitionPostController {

    private LookupDefinitionService lookupDefinitionService;
    private LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer;

    @Autowired
    public AddLookupDefinitionPostController(LookupDefinitionService lookupDefinitionService, LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer) {
        super();
        this.lookupDefinitionService = lookupDefinitionService;
        this.lookupDefinitionViewDomainTransformer = lookupDefinitionViewDomainTransformer;
    }

    @RequestMapping
    public ResponseEntity<?> addLookupDefinition(LookupDefinitionPostRequest request) {
        LookupDefinition lookupDefinition = lookupDefinitionViewDomainTransformer.postRequestToDomain(request);
        lookupDefinition = lookupDefinitionService.addLookupDefinition(lookupDefinition);
        HttpHeaders httpHeaders = new HttpHeaders();
        URI createdResourceUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/lookup-definitions/{id}")
                .buildAndExpand(lookupDefinition.getId())
                .toUri();
        httpHeaders.setLocation(createdResourceUri);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

}
