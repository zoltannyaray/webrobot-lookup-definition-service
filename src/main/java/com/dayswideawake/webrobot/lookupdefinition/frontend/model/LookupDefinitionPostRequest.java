package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

public class LookupDefinitionPostRequest {

    private Long accountId;
    private SitePostRequest site;
    private SelectorPostRequest selector;
    private Long intervalInSeconds;

    public LookupDefinitionPostRequest() {
        super();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public SitePostRequest getSite() {
        return site;
    }

    public void setSite(SitePostRequest site) {
        this.site = site;
    }

    public SelectorPostRequest getSelector() {
        return selector;
    }

    public void setSelector(SelectorPostRequest selector) {
        this.selector = selector;
    }

    public Long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(Long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

}
