package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddSiteRequest;

@Component
public class SiteViewDomainTransformer {

    public Site postRequestToDomain(AddSiteRequest postRequest){
        return new Site();
    }
    
}
