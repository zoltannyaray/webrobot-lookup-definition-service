package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class LookupDefinitionResource extends Resource<LookupDefinitionDetails> {

	public LookupDefinitionResource(LookupDefinitionDetails content, Link... links) {
		super(content, links);
	}

	public LookupDefinitionResource(LookupDefinitionDetails content, Iterable<Link> links) {
		super(content, links);
	}

}
