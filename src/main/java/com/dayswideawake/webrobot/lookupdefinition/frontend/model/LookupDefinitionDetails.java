package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LookupDefinitionDetails {

    private Long id;
    private Long accountId;
    private Long intervalInSeconds;
    private SelectorDetails selector;
    private SiteDetails site;

    @JsonCreator
    public LookupDefinitionDetails() {
    }

    public LookupDefinitionDetails(Long id, Long accountId, Long intervalInSeconds, SelectorDetails selector, SiteDetails site) {
        this.id = id;
        this.accountId = accountId;
        this.intervalInSeconds = intervalInSeconds;
        this.selector = selector;
        this.site = site;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(Long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

    public SelectorDetails getSelector() {
        return selector;
    }

    public SiteDetails getSite() {
        return site;
    }

}
