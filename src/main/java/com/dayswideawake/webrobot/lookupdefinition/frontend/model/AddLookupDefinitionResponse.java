package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

import org.springframework.hateoas.ResourceSupport;

public class AddLookupDefinitionResponse extends ResourceSupport {

    private LookupDefinitionDetails content;
    
    public AddLookupDefinitionResponse(LookupDefinitionDetails content) {
        this.content = content;
    }

    public LookupDefinitionDetails getContent() {
        return content;
    }
    
}
