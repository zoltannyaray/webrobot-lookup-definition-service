package com.dayswideawake.webrobot.lookupdefinition.backend.domain;

public class SelectorXPath extends Selector {

    private String selector;

    public SelectorXPath(String selector) {
        super();
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

}
