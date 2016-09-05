package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionResource;
import com.dayswideawake.webrobot.lookupdefinition.frontend.transformer.LookupDefinitionResourceAssembler;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@RestController
@RequestMapping(path = LookupDefinitionUrls.LIST_BY_ACCOUNT_ID)
public class ListLookupDefinitionsByAccountIdController {

	private LookupDefinitionService lookupDefinitionService;
	private PagedResourcesAssembler<LookupDefinition> pagedResourcesAssembler;
	private LookupDefinitionResourceAssembler lookupDefinitionResourceAssembler;

	@Autowired
	public ListLookupDefinitionsByAccountIdController(LookupDefinitionService lookupDefinitionService, PagedResourcesAssembler<LookupDefinition> pagedResourcesAssembler, LookupDefinitionResourceAssembler lookupDefinitionResourceAssembler) {
		this.lookupDefinitionService = lookupDefinitionService;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
		this.lookupDefinitionResourceAssembler = lookupDefinitionResourceAssembler;
	}

	@RequestMapping(method = RequestMethod.GET)
	public PagedResources<LookupDefinitionResource> handle(@PathVariable Long accountId, Pageable pageable) {
		Page<LookupDefinition> lookupDefinitions = lookupDefinitionService.getLookupDefinitionsByAccountId(accountId, pageable);
		return pagedResourcesAssembler.toResource(lookupDefinitions, lookupDefinitionResourceAssembler);
	}

}
