package com.dayswideawake.webrobot.lookupdefinition.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LookupDefinitionCreatedEventMessage {

	private Long lookupDefinitionId;
	private Long intervalInSeconds;
	private Long accountId;

	@JsonCreator
	public LookupDefinitionCreatedEventMessage() {
	}

	public LookupDefinitionCreatedEventMessage(Long lookupDefinitionId) {
		this.lookupDefinitionId = lookupDefinitionId;
	}

	private LookupDefinitionCreatedEventMessage(Builder builder) {
		this.lookupDefinitionId = builder.lookupDefinitionId;
		this.intervalInSeconds = builder.intervalInSeconds;
		this.accountId = builder.accountId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public void setLookupDefinitionId(Long lookupDefinitionId) {
		this.lookupDefinitionId = lookupDefinitionId;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public Long getAccountId() {
		return accountId;
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long intervalInSeconds;
		private Long accountId;

		public Builder(Long lookupDefinitionId, Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder accountId(Long accountId) {
			this.accountId = accountId;
			return this;
		}

		public LookupDefinitionCreatedEventMessage build() {
			return new LookupDefinitionCreatedEventMessage(this);
		}

	}

}
