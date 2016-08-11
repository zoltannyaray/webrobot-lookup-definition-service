package com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity;

import javax.persistence.Entity;

@Entity
public class SelectorCssEntity extends SelectorEntity {

    private String selector;

    public SelectorCssEntity() {
        super();
    }

    public SelectorCssEntity(String selector) {
        super();
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

}
