package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionPostRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionPostResponse;

@Component
public class LookupDefinitionViewDomainTransformer {

    private SiteViewDomainTransformer sitePostRequestDomainTransformer;
    private SelectorViewDomainTransformer selectorPostRequestDomainTransformer;

    @Autowired
    public LookupDefinitionViewDomainTransformer(SiteViewDomainTransformer sitePostRequestDomainTransformer, SelectorViewDomainTransformer selectorPostRequestDomainTransformer) {
        super();
        this.sitePostRequestDomainTransformer = sitePostRequestDomainTransformer;
        this.selectorPostRequestDomainTransformer = selectorPostRequestDomainTransformer;
    }

    public LookupDefinition postRequestToDomain(LookupDefinitionPostRequest addLookupDefinitionRequest) {
        Site site = sitePostRequestDomainTransformer.postRequestToDomain(addLookupDefinitionRequest.getSite());
        Selector selector = selectorPostRequestDomainTransformer.postRequestToDomain(addLookupDefinitionRequest.getSelector());
        Long intervalInSeconds = addLookupDefinitionRequest.getIntervalInSeconds();
        LookupDefinition result = new LookupDefinition(site, selector, intervalInSeconds);
        if (addLookupDefinitionRequest.getAccountId() != null) {
            result.setAccountId(addLookupDefinitionRequest.getAccountId());
        }
        return result;
    }
    
    public LookupDefinitionPostResponse domainToPostResponse(LookupDefinition lookupDefinition){
        return new LookupDefinitionPostResponse();
    }

}
