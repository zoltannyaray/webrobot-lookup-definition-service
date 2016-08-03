package com.dayswideawake.webrobot.lookupdefinition.backend.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorEntity;

@Component
public class SelectorDomainToEntityConverter implements Converter<Selector, SelectorEntity>{

    @Override
    public SelectorEntity convert(Selector source) {
        return new SelectorEntity();
    }

}
