package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

public class SelectorDetails {

    private String selector;
    private SelectorTypeModel selectorType;

    public SelectorDetails() {
    }

    public SelectorDetails(String selector, SelectorTypeModel selectorType) {
        this.selector = selector;
        this.selectorType = selectorType;
    }

    public String getSelector() {
        return selector;
    }

    public SelectorTypeModel getSelectorType() {
        return selectorType;
    }

}
