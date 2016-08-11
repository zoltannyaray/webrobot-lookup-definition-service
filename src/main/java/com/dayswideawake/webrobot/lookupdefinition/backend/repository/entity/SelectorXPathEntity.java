package com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity;

import javax.persistence.Entity;

@Entity
public class SelectorXPathEntity extends SelectorEntity {

    private String selector;

    public SelectorXPathEntity() {
        super();
    }

    public SelectorXPathEntity(String selector) {
        super();
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

}
