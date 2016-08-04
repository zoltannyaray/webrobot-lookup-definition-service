package com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SiteEntity;

@Component
public class LookupDefinitionDomainEntityTransformer {

    private SiteDomainEntityTransformer siteDomainEntityTransformer;
    private SelectorDomainEntityTransformer selectorDomainEntityTransformer;
    
    @Autowired
    public LookupDefinitionDomainEntityTransformer(SiteDomainEntityTransformer siteDomainEntityTransformer, SelectorDomainEntityTransformer selectorDomainEntityTransformer) {
        super();
        this.siteDomainEntityTransformer = siteDomainEntityTransformer;
        this.selectorDomainEntityTransformer = selectorDomainEntityTransformer;
    }

    public LookupDefinitionEntity domainToEntity(LookupDefinition lookupDefinition) {
        SiteEntity siteEntity = siteDomainEntityTransformer.domainToEntity(lookupDefinition.getSite());
        SelectorEntity selectorEntity = selectorDomainEntityTransformer.domainToEntity(lookupDefinition.getSelector());
        Long interval = lookupDefinition.getIntervalInSeconds();
        LookupDefinitionEntity lookupDefinitionEntity =new LookupDefinitionEntity(siteEntity, selectorEntity, interval); 
        if(lookupDefinition.getAccountId() != null){
            lookupDefinitionEntity.setAccountId(lookupDefinition.getAccountId());
        }
        if(lookupDefinition.getLastLookupAt() != null){
            lookupDefinitionEntity.setLastLookupAt(lookupDefinition.getLastLookupAt().getTime());
        }
        return lookupDefinitionEntity;
    }
    
    public LookupDefinition entityToDomain(LookupDefinitionEntity lookupDefinitionEntity) {
        Site site = siteDomainEntityTransformer.entityToDomain(lookupDefinitionEntity.getSite());
        Selector selector = selectorDomainEntityTransformer.entityToDomain(lookupDefinitionEntity.getSelector());
        Long intervalInSeconds = lookupDefinitionEntity.getIntervalInSeconds();
        LookupDefinition lookupDefinition = new LookupDefinition(site, selector, intervalInSeconds);
        if(lookupDefinitionEntity.getAccountId() != null){
            lookupDefinition.setAccountId(lookupDefinitionEntity.getAccountId());
        }
        if(lookupDefinitionEntity.getLastLookupAt() != null){
            lookupDefinition.setLastLookupAt(new Date(lookupDefinitionEntity.getLastLookupAt()));
        }
        return lookupDefinition; 
    }

}
