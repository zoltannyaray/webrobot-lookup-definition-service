package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionDetails;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SelectorDetails;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SiteDetails;

@Component
public class LookupDefinitionViewDomainTransformer {

    private SiteViewDomainTransformer siteViewDomainTransformer;
    private SelectorViewDomainTransformer selectorViewDomainTransformer;

    @Autowired
    public LookupDefinitionViewDomainTransformer(SiteViewDomainTransformer sitePostRequestDomainTransformer, SelectorViewDomainTransformer selectorPostRequestDomainTransformer) {
        this.siteViewDomainTransformer = sitePostRequestDomainTransformer;
        this.selectorViewDomainTransformer = selectorPostRequestDomainTransformer;
    }

    public LookupDefinition postRequestToDomain(AddLookupDefinitionRequest addLookupDefinitionRequest) {
        Site site = siteViewDomainTransformer.postRequestToDomain(addLookupDefinitionRequest.getSite());
        Selector selector = selectorViewDomainTransformer.addRequestToDomain(addLookupDefinitionRequest.getSelector());
        Long intervalInSeconds = addLookupDefinitionRequest.getIntervalInSeconds();
        return new LookupDefinition.Builder(site, selector, intervalInSeconds)
        		.accountId(addLookupDefinitionRequest.getAccountId())
        		.build();
    }

    public LookupDefinitionDetails domainToDetails(LookupDefinition domain){
        Long id = domain.getId();
        Long accountId = domain.getAccountId();
        Long intervalInSeconds = domain.getIntervalInSeconds();
        SelectorDetails selector = selectorViewDomainTransformer.domainToDetails(domain.getSelector());
        SiteDetails site = siteViewDomainTransformer.domainToDetails(domain.getSite());
        return new LookupDefinitionDetails(id, accountId, intervalInSeconds, selector, site);
    }

}
