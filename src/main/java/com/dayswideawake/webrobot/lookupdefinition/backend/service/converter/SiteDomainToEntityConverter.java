package com.dayswideawake.webrobot.lookupdefinition.backend.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SiteEntity;

@Component
public class SiteDomainToEntityConverter implements Converter<Site, SiteEntity>{

    @Override
    public SiteEntity convert(Site source) {
        return new SiteEntity();
    }

}
