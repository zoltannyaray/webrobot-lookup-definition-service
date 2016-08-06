package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer.LookupDefinitionDomainEntityTransformer;

@Service
public class LookupDefinitionServiceImpl implements LookupDefinitionService {

    private LookupDefinitionRepository repository;
    private LookupDefinitionDomainEntityTransformer domainEntityTransformer;
    private Logger logger = Logger.getLogger(LookupDefinitionServiceImpl.class.getName()); 

    @Autowired
    public LookupDefinitionServiceImpl(LookupDefinitionRepository repository, LookupDefinitionDomainEntityTransformer domainEntityTransformer) {
        super();
        this.repository = repository;
        this.domainEntityTransformer = domainEntityTransformer;
    }

    @Override
    public LookupDefinition addLookupDefinition(LookupDefinition lookupDefinition) {
        logger.log(Level.INFO, "CALL addLookupDefinition");
        LookupDefinitionEntity entity = domainEntityTransformer.domainToEntity(lookupDefinition);
        entity = repository.save(entity);
        logger.log(Level.INFO, "Saved Entity ID: " + entity.getId());
        return domainEntityTransformer.entityToDomain(entity);
    }

    @Override
    public List<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId) {
        List<LookupDefinitionEntity> entities = repository.findAll();
        return entities
                .stream()
                .map(entity -> domainEntityTransformer.entityToDomain(entity))
                .collect(Collectors.toList());
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
