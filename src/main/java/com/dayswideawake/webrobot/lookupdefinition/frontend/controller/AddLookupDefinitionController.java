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
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionResource;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionResourceAssembler;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionViewDomainTransformer;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@RestController
@RequestMapping(path = LookupDefinitionUrls.BASE_URL)
public class AddLookupDefinitionController {

	private LookupDefinitionService lookupDefinitionService;
	private LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer;
	private LookupDefinitionResourceAssembler lookupDefinitionResourceAssembler;

	@Autowired
	public AddLookupDefinitionController(LookupDefinitionService lookupDefinitionService, LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer, LookupDefinitionResourceAssembler lookupDefinitionResourceAssembler) {
		this.lookupDefinitionService = lookupDefinitionService;
		this.lookupDefinitionViewDomainTransformer = lookupDefinitionViewDomainTransformer;
		this.lookupDefinitionResourceAssembler = lookupDefinitionResourceAssembler;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<LookupDefinitionResource> addLookupDefinition(@RequestBody AddLookupDefinitionRequest request) {
		LookupDefinition lookupDefinition = lookupDefinitionViewDomainTransformer.postRequestToDomain(request);
		lookupDefinition = lookupDefinitionService.addLookupDefinition(lookupDefinition);
		LookupDefinitionResource lookupDefinitionResource = lookupDefinitionResourceAssembler.toResource(lookupDefinition);
		URI createdResourceUri = UriComponentsBuilder.fromPath(lookupDefinitionResource.getLink(Link.REL_SELF).getHref()).build().toUri();
		return ResponseEntity.created(createdResourceUri).body(lookupDefinitionResource);
	}

}
