package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.frontend.controller.ViewLookupDefinitionController;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionDetails;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionResource;

@Component
public class LookupDefinitionResourceAssembler extends ResourceAssemblerSupport<LookupDefinition, LookupDefinitionResource>{

	private LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer;
	
	@Autowired
	public LookupDefinitionResourceAssembler(LookupDefinitionViewDomainTransformer lookupDefinitionViewDomainTransformer) {
		super(ViewLookupDefinitionController.class, LookupDefinitionResource.class);
		this.lookupDefinitionViewDomainTransformer = lookupDefinitionViewDomainTransformer;
	}

	@Override
	public LookupDefinitionResource toResource(LookupDefinition entity) {
		return createResourceWithId(entity.getId(), entity);
	}

	@Override
	protected LookupDefinitionResource instantiateResource(LookupDefinition entity) {
		LookupDefinitionDetails lookupDefinitionDetails = lookupDefinitionViewDomainTransformer.domainToDetails(entity);
		return new LookupDefinitionResource(lookupDefinitionDetails);
	}
	
	

}
