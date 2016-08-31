package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionResponse;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionViewDomainTransformer;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@RestController
public class AddLookupDefinitionController {

    private LookupDefinitionService lookupDefinitionService;
    private LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer;

    @Autowired
    public AddLookupDefinitionController(LookupDefinitionService lookupDefinitionService, LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer) {
        this.lookupDefinitionService = lookupDefinitionService;
        this.lookupDefinitionViewDomainTransformer = lookupDefinitionViewDomainTransformer;
    }

    @RequestMapping(path = LookupDefinitionUrls.BASE_URL, method = RequestMethod.POST)
    public ResponseEntity<AddLookupDefinitionResponse> addLookupDefinition(@RequestBody AddLookupDefinitionRequest request) {
        LookupDefinition lookupDefinition = lookupDefinitionViewDomainTransformer.postRequestToDomain(request);
        lookupDefinition = lookupDefinitionService.addLookupDefinition(lookupDefinition);
        AddLookupDefinitionResponse response = lookupDefinitionViewDomainTransformer.domainToPostResponse(lookupDefinition);
        URI createdResourceUri = UriComponentsBuilder.fromPath(response.getLink(Link.REL_SELF).getHref()).build().toUri();
        return ResponseEntity.created(createdResourceUri).body(response);
    }

}
