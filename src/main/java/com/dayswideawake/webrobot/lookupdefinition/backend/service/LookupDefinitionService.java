package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.List;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;

public interface LookupDefinitionService {

    public List<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId);

    LookupDefinition addLookupDefinition(LookupDefinition lookupDefintion);
    
}
