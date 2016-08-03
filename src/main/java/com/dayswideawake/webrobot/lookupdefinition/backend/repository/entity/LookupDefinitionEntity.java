package com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LookupDefinitionEntity {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade=CascadeType.ALL)
    private SiteEntity site;
    @OneToOne(cascade=CascadeType.ALL)
    private SelectorEntity selector;
    private Long intervalInSeconds;
    private Long lastLookupAt;
    
    public LookupDefinitionEntity() {
        super();
    }
    
    public LookupDefinitionEntity(SiteEntity site, SelectorEntity selector, Long intervalInSeconds) {
        this();
        this.site = site;
        this.selector = selector;
        this.intervalInSeconds = intervalInSeconds;
    }

    public LookupDefinitionEntity(SiteEntity site, SelectorEntity selector, Long intervalInSeconds, Long lastLookupAt) {
        this(site, selector, intervalInSeconds);
        this.lastLookupAt = lastLookupAt;
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
