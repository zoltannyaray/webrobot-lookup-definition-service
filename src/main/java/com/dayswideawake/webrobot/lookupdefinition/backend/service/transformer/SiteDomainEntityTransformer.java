package com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer;

import java.net.URL;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SiteEntity;

@Component
public class SiteDomainEntityTransformer {

    public SiteEntity domainToEntity(Site domain) {
        URL url = domain.getUrl();
        return new SiteEntity(url);
    }

    public Site entityToDomain(SiteEntity entity) {
        URL url = entity.getUrl();
        return new Site(url);
    }

}
