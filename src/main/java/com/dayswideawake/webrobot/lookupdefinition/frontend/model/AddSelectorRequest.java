package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

public class AddSelectorRequest {

	private String selector;
	private SelectorTypeModel selectorType;

	public AddSelectorRequest() {
	}

	public AddSelectorRequest(String selector, SelectorTypeModel selectorType) {
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
