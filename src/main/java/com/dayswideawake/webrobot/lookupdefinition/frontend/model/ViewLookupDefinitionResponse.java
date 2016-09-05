package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ViewLookupDefinitionResponse extends Resource<LookupDefinitionDetails> {

	public ViewLookupDefinitionResponse(LookupDefinitionDetails content, Link... links) {
		super(content, links);
	}

	public ViewLookupDefinitionResponse(LookupDefinitionDetails content, Iterable<Link> links) {
		super(content, links);
	}
  
}
