package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.List;
import java.util.Optional;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;

public interface LookupDefinitionService {

    public List<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId);

    LookupDefinition addLookupDefinition(LookupDefinition lookupDefinition);
    
    Optional<LookupDefinition> getLookupDefinitionById(Long id);
    
}
