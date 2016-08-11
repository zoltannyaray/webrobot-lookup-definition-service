package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

public class AddLookupDefinitionRequest {

    private Long accountId;
    private AddSiteRequest site;
    private AddSelectorRequest selector;
    private Long intervalInSeconds;

    public AddLookupDefinitionRequest() {
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
