package com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorEntity;

@Component
public class SelectorDomainEntityTransformer {

    public SelectorEntity domainToEntity(Selector source) {
        return new SelectorEntity();
    }

    public Selector entityToDomain(SelectorEntity source) {
        return new Selector();
    }

}
