package com.dayswideawake.webrobot.lookupdefinition.backend.domain;

public class SelectorCss extends Selector {

    private String selector;

    public SelectorCss(String selector) {
        super();
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

}
