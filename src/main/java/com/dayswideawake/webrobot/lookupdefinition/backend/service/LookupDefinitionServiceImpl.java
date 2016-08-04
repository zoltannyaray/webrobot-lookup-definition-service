package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;

@Service
public class LookupDefinitionServiceImpl implements LookupDefinitionService {

    private LookupDefinitionRepository lookupDefinitionRepository;
    private ConversionService conversionService;
    
    @Autowired
    public LookupDefinitionServiceImpl(LookupDefinitionRepository lookupDefinitionRepository, ConversionService conversionService) {
        super();
        this.lookupDefinitionRepository = lookupDefinitionRepository;
        this.conversionService = conversionService;
    }

    @Override
    public LookupDefinition addLookupDefinition(LookupDefinition lookupDefinition){
        LookupDefinitionEntity entity = conversionService.convert(lookupDefinition, LookupDefinitionEntity.class);
        entity = lookupDefinitionRepository.save(entity);
        return conversionService.convert(entity, LookupDefinition.class);
    }
    
    @Override
    public List<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId) {
        List<LookupDefinitionEntity> entities = lookupDefinitionRepository.findAll();
        return entities
                .stream()
                .map(entity -> conversionService.convert(entity, LookupDefinition.class))
                .collect(Collectors.toList());
//        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(LookupDefintionEntity.class));
//        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(LookupDefintion.class));
//        return (List<LookupDefintion>) conversionService.convert(entities, sourceType, targetType);
    }

}
