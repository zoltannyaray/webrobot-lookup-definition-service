package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SitePostRequest;

@Component
public class SiteViewDomainTransformer {

    public Site postRequestToDomain(SitePostRequest postRequest){
        return new Site();
    }
    
}
