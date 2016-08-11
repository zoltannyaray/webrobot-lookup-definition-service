package com.dayswideawake.webrobot.lookupdefinition.frontend.model;

import org.springframework.hateoas.ResourceSupport;

public class ViewLookupDefinitionResponse extends ResourceSupport {

    private LookupDefinitionDetails content;

    public ViewLookupDefinitionResponse(LookupDefinitionDetails content) {
        this.content = content;
    }

    public LookupDefinitionDetails getContent() {
        return content;
    }
    
    
}
