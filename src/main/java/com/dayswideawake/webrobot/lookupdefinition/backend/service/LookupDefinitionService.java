package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.List;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefintion;

public interface LookupDefinitionService {

    public List<LookupDefintion> getLookupDefinitionsByAccount(Long accountId);
    
}
