package com.dayswideawake.webrobot.lookupdefinition.messaging.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.event.LookupDefinitionCreatedEvent;
import com.dayswideawake.webrobot.lookupdefinition.messaging.model.LookupDefinitionCreatedEventMessage;

@Component
public class LookupDefinitionDomainMessageTransformer {

	public LookupDefinitionCreatedEventMessage eventToMessage(LookupDefinitionCreatedEvent lookupDefinitionCreatedEvent){
		Long lookupDefinitionId = lookupDefinitionCreatedEvent.getLookupDefinitionId();
		return new LookupDefinitionCreatedEventMessage(lookupDefinitionId);
	}
}
