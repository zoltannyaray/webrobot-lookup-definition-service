package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class AddLookupDefinitionResponse extends Resource<LookupDefinitionDetails> {

	public AddLookupDefinitionResponse(LookupDefinitionDetails content, Link... links) {
		super(content, links);
	}

	public AddLookupDefinitionResponse(LookupDefinitionDetails content, Iterable<Link> links) {
		super(content, links);
	}

        
}
