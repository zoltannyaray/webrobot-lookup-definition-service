package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LookupDefinitionDetails extends ResourceSupport {

    @JsonCreator
    public LookupDefinitionDetails() {
        super();
    }

}
