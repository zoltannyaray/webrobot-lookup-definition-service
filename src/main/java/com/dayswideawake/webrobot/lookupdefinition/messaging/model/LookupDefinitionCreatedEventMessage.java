package com.dayswideawake.webrobot.lookupdefinition.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LookupDefinitionCreatedEventMessage {

	private Long lookupDefinitionId;

	@JsonCreator
	public LookupDefinitionCreatedEventMessage() {
	}

	public LookupDefinitionCreatedEventMessage(Long lookupDefinitionId) {
		this.lookupDefinitionId = lookupDefinitionId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public void setLookupDefinitionId(Long lookupDefinitionId) {
		this.lookupDefinitionId = lookupDefinitionId;
	}

}
