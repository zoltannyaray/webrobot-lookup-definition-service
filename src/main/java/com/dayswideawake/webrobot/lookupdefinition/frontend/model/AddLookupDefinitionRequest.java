package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

public class AddLookupDefinitionRequest {

	private Long accountId;
	private AddSiteRequest site;
	private AddSelectorRequest selector;
	private Long intervalInSeconds;

	public AddLookupDefinitionRequest() {
	}

	public AddLookupDefinitionRequest(Long accountId, AddSiteRequest site, AddSelectorRequest selector, Long intervalInSeconds) {
		this.accountId = accountId;
		this.site = site;
		this.selector = selector;
		this.intervalInSeconds = intervalInSeconds;
	}

	public Long getAccountId() {
		return accountId;
	}

	public AddSiteRequest getSite() {
		return site;
	}

	public AddSelectorRequest getSelector() {
		return selector;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

}
