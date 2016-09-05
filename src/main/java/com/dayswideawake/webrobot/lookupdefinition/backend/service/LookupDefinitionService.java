package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;

public interface LookupDefinitionService {

    Page<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId, Pageable pageable);

    LookupDefinition addLookupDefinition(LookupDefinition lookupDefinition);
    
    Optional<LookupDefinition> getLookupDefinitionById(Long id);
    
    
    
}
