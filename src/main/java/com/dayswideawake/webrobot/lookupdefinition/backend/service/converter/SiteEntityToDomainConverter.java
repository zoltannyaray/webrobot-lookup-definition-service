package com.dayswideawake.webrobot.lookupdefinition.backend.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SiteEntity;

@Component
public class SiteEntityToDomainConverter implements Converter<SiteEntity, Site>{

    @Override
    public Site convert(SiteEntity source) {
        return new Site();
    }

}
