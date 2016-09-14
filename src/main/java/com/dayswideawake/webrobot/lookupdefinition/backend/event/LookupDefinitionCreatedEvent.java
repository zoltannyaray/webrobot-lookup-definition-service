package com.dayswideawake.webrobot.lookupdefinition.backend.event;

public class LookupDefinitionCreatedEvent {

	private Long lookupDefinitionId;

	public LookupDefinitionCreatedEvent(Long lookupDefinitionId) {
		this.lookupDefinitionId = lookupDefinitionId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}
	
	
	
}
