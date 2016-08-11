package com.dayswideawake.webrobot.lookupdefinition.frontend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class LookupDefinitionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LookupDefinitionNotFoundException(Long id) {
        super("Lookup definition #" + id + " not found");
    }

    
    
}
