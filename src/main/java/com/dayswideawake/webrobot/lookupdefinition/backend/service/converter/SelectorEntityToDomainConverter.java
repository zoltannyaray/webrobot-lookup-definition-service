package com.dayswideawake.webrobot.lookupdefinition.backend.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorEntity;

@Component
public class SelectorEntityToDomainConverter implements Converter<SelectorEntity, Selector>{

    @Override
    public Selector convert(SelectorEntity source) {
        return new Selector();
    }

}
