package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer.LookupDefinitionDomainEntityTransformer;

@Service
public class LookupDefinitionServiceImpl implements LookupDefinitionService {

    private LookupDefinitionRepository lookupDefinitionRepository;
    private LookupDefinitionDomainEntityTransformer lookupDefinitionDomainEntityTransformer;

    @Autowired
    public LookupDefinitionServiceImpl(LookupDefinitionRepository lookupDefinitionRepository, LookupDefinitionDomainEntityTransformer lookupDefinitionDomainEntityTransformer) {
        super();
        this.lookupDefinitionRepository = lookupDefinitionRepository;
        this.lookupDefinitionDomainEntityTransformer = lookupDefinitionDomainEntityTransformer;
    }

    @Override
    public LookupDefinition addLookupDefinition(LookupDefinition lookupDefinition) {
        LookupDefinitionEntity entity = lookupDefinitionDomainEntityTransformer.domainToEntity(lookupDefinition);
        entity = lookupDefinitionRepository.save(entity);
        return lookupDefinitionDomainEntityTransformer.entityToDomain(entity);
    }

    @Override
    public List<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId) {
        List<LookupDefinitionEntity> entities = lookupDefinitionRepository.findAll();
        return entities
                .stream()
                .map(entity -> lookupDefinitionDomainEntityTransformer.entityToDomain(entity))
                .collect(Collectors.toList());
    }

}
