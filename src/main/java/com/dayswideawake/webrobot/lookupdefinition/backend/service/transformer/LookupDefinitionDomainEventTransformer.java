package com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.event.LookupDefinitionCreatedEvent;

@Component
public class LookupDefinitionDomainEventTransformer {

	public LookupDefinitionCreatedEvent domainToCreatedEvent(LookupDefinition lookupDefinition){
		Long lookupDefinitionId = lookupDefinition.getId();
		return new LookupDefinitionCreatedEvent(lookupDefinitionId);
	}
	
}
