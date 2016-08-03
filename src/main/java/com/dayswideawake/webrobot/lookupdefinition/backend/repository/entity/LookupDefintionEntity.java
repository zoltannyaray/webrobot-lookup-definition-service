package com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LookupDefintionEntity {

    @Id
    @GeneratedValue
    private Long id;
    private SiteEntity site;
    private SelectorEntity selector;
    private Long intervalInSeconds;
    private Long lastLookupAt;
    
    public LookupDefintionEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public SelectorEntity getSelector() {
        return selector;
    }

    public void setSelector(SelectorEntity selector) {
        this.selector = selector;
    }

    public Long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(Long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

    public Long getLastLookupAt() {
        return lastLookupAt;
    }

    public void setLastLookupAt(Long lastLookupAt) {
        this.lastLookupAt = lastLookupAt;
    }
    
    
    
    
    
}
