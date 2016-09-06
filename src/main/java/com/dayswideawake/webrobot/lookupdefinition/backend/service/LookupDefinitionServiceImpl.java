package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer.LookupDefinitionDomainEntityTransformer;

@Service
public class LookupDefinitionServiceImpl implements LookupDefinitionService {

    private LookupDefinitionRepository repository;
    private LookupDefinitionDomainEntityTransformer domainEntityTransformer;

    @Autowired
    public LookupDefinitionServiceImpl(LookupDefinitionRepository repository, LookupDefinitionDomainEntityTransformer domainEntityTransformer) {
        this.repository = repository;
        this.domainEntityTransformer = domainEntityTransformer;
    }

    @Override
    public LookupDefinition addLookupDefinition(LookupDefinition lookupDefinition) {
        LookupDefinitionEntity entity = domainEntityTransformer.domainToEntity(lookupDefinition);
        entity = repository.save(entity);
        return domainEntityTransformer.entityToDomain(entity);
    }

    @Override
    public Page<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId, Pageable pageable) {
        Page<LookupDefinitionEntity> entityPage = repository.findByAccountId(accountId, pageable);
        return entityPage.map(domainEntityTransformer);
    }

    @Override
    public Optional<LookupDefinition> getLookupDefinitionById(Long id) {
        LookupDefinitionEntity entity = repository.findOne(id);
        LookupDefinition lookupDefinition = null;
        if(entity != null){
            lookupDefinition = domainEntityTransformer.entityToDomain(entity);
        }
        return Optional.ofNullable(lookupDefinition);
    }

}
