package com.dayswideawake.webrobot.lookupdefinition.backend.event;

public class LookupDefinitionCreatedEvent {

	private Long lookupDefinitionId;
	private Long accountId;
	private Long intervalInSeconds;

	public LookupDefinitionCreatedEvent(Long lookupDefinitionId) {
		this.lookupDefinitionId = lookupDefinitionId;
	}

	private LookupDefinitionCreatedEvent(Builder builder) {
		this.lookupDefinitionId = builder.lookupDefinitionId;
		this.accountId = builder.accountId;
		this.intervalInSeconds = builder.intervalInSeconds;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long accountId;
		private Long intervalInSeconds;

		public Builder(Long lookupDefinitionId, Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder accountId(Long accountId) {
			this.accountId = accountId;
			return this;
		}

		public LookupDefinitionCreatedEvent build() {
			return new LookupDefinitionCreatedEvent(this);
		}

	}

}
