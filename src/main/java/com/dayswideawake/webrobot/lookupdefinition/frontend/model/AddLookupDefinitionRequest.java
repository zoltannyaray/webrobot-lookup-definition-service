package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

public class AddLookupDefinitionRequest {

    private Long accountId;
    private SiteModel site;
    private SelectorModel selector;
    private Long intervalInSeconds;

    public AddLookupDefinitionRequest() {
        super();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public SiteModel getSite() {
        return site;
    }

    public void setSite(SiteModel site) {
        this.site = site;
    }

    public SelectorModel getSelector() {
        return selector;
    }

    public void setSelector(SelectorModel selector) {
        this.selector = selector;
    }

    public Long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(Long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

}
