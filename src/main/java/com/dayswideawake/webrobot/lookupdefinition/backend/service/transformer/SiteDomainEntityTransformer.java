package com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SiteEntity;

@Component
public class SiteDomainEntityTransformer {

    public SiteEntity domainToEntity(Site source) {
        return new SiteEntity();
    }

    public Site entityToDomain(SiteEntity source) {
        return new Site();
    }

}
