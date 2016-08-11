package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.frontend.controller.ViewLookupDefinitionController;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.LookupDefinitionDetails;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SelectorDetails;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SiteDetails;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionResponse;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.ViewLookupDefinitionResponse;

@Component
public class LookupDefinitionViewDomainTransformer {

    private SiteViewDomainTransformer siteViewDomainTransformer;
    private SelectorViewDomainTransformer selectorViewDomainTransformer;

    @Autowired
    public LookupDefinitionViewDomainTransformer(SiteViewDomainTransformer sitePostRequestDomainTransformer, SelectorViewDomainTransformer selectorPostRequestDomainTransformer) {
        super();
        this.siteViewDomainTransformer = sitePostRequestDomainTransformer;
        this.selectorViewDomainTransformer = selectorPostRequestDomainTransformer;
    }

    public LookupDefinition postRequestToDomain(AddLookupDefinitionRequest addLookupDefinitionRequest) {
        Site site = siteViewDomainTransformer.postRequestToDomain(addLookupDefinitionRequest.getSite());
        Selector selector = selectorViewDomainTransformer.addRequestToDomain(addLookupDefinitionRequest.getSelector());
        Long intervalInSeconds = addLookupDefinitionRequest.getIntervalInSeconds();
        LookupDefinition result = new LookupDefinition(site, selector, intervalInSeconds);
        if (addLookupDefinitionRequest.getAccountId() != null) {
            result.setAccountId(addLookupDefinitionRequest.getAccountId());
        }
        return result;
    }

    public AddLookupDefinitionResponse domainToPostResponse(LookupDefinition lookupDefinition) {
        LookupDefinitionDetails responseContent = domainToDetails(lookupDefinition);
        AddLookupDefinitionResponse response = new AddLookupDefinitionResponse(responseContent);
        response.add(linkTo(methodOn(ViewLookupDefinitionController.class).view(lookupDefinition.getId())).withSelfRel());
        return response;
    }
    
    public ViewLookupDefinitionResponse domainToViewResponse(LookupDefinition lookupDefinition){
        ViewLookupDefinitionResponse response = new ViewLookupDefinitionResponse(domainToDetails(lookupDefinition));
        response.add(linkTo(methodOn(ViewLookupDefinitionController.class).view(lookupDefinition.getId())).withSelfRel());
        return response;
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
