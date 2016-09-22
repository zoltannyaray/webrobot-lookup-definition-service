package com.dayswideawake.webrobot.lookupdefinition.backend.domain;

import java.util.Date;

public class LookupDefinition {

	private Long id;
	private Long accountId;
	private Site site;
	private Selector selector;
	private Long intervalInSeconds;
	private Date lastLookupAt;

	private LookupDefinition(Builder builder) {
		id = builder.id;
		accountId = builder.accountId;
		site = builder.site;
		selector = builder.selector;
		intervalInSeconds = builder.intervalInSeconds;
		lastLookupAt = builder.lastLookupAt;
	}

	public Long getId() {
		return id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Site getSite() {
		return site;
	}

	public Selector getSelector() {
		return selector;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public Date getLastLookupAt() {
		return lastLookupAt;
	}

	public static class Builder {
		private Long id;
		private Long accountId;
		private final Site site;
		private final Selector selector;
		private final Long intervalInSeconds;
		private Date lastLookupAt;

		public Builder(Site site, Selector selector, Long intervalInSeconds) {
			this.site = site;
			this.selector = selector;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder accountId(Long accountId) {
			this.accountId = accountId;
			return this;
		}

		public Builder lastLookupAt(Date lastLookupAt) {
			this.lastLookupAt = lastLookupAt;
			return this;
		}

		public Builder lastLookupAt(Long lastLookupAt) {
			if (lastLookupAt != null) {
				this.lastLookupAt = new Date(lastLookupAt);
			}
			return this;
		}

		public LookupDefinition build() {
			return new LookupDefinition(this);
		}

	}

}
