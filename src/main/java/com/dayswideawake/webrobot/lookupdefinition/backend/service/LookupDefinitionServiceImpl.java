package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefintion;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefintionEntity;

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
    public List<LookupDefintion> getLookupDefinitionsByAccount(Long accountId) {
        List<LookupDefintionEntity> entities = lookupDefinitionRepository.findAll();
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(LookupDefintionEntity.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(LookupDefintion.class));
        return conversionService.convert(entities, sourceType, targetType);
    }

}
