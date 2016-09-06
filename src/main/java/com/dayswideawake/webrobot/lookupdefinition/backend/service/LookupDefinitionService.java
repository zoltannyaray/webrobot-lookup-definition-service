package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;

public interface LookupDefinitionService {

	Optional<LookupDefinition> getLookupDefinitionById(Long id);

	LookupDefinition addLookupDefinition(LookupDefinition lookupDefinition);

	Page<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId, Pageable pageable);
	
	Page<LookupDefinition> getLookupDefinitionsForSchedule(Pageable pageable);

}
