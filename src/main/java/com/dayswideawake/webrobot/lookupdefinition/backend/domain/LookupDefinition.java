package com.dayswideawake.webrobot.lookupdefinition.backend.domain;

import java.util.Date;

public class LookupDefinition {

    private Long accountId;
    private Site site;
    private Selector selector;
    private Long intervalInSeconds;
    private Date lastLookupAt;

    public LookupDefinition(Site site, Selector selector, Long intervalInSeconds, Date lastLookupAt) {
        super();
        this.site = site;
        this.selector = selector;
        this.intervalInSeconds = intervalInSeconds;
        this.lastLookupAt = lastLookupAt;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public Long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(Long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

    public Date getLastLookupAt() {
        return lastLookupAt;
    }

    public void setLastLookupAt(Date lastLookupAt) {
        this.lastLookupAt = lastLookupAt;
    }

}
