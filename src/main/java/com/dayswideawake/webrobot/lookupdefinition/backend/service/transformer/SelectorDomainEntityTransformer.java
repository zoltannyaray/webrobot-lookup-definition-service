package com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorXPath;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorCssEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorXPathEntity;

@Component
public class SelectorDomainEntityTransformer {

    public SelectorEntity domainToEntity(Selector selector) {
        SelectorEntity entity;
        if(selector instanceof SelectorCss){
            entity = new SelectorCssEntity(((SelectorCss) selector).getSelector());
        }
        else if(selector instanceof SelectorXPath){
            entity = new SelectorXPathEntity(((SelectorXPath) selector).getSelector());
        }
        else {
            throw new IllegalArgumentException("Given domain type is not supported");
        }
        return entity;
    }

    public Selector entityToDomain(SelectorEntity entity) {
        Selector selector;
        if(entity instanceof SelectorCssEntity){
            selector = new SelectorCss(((SelectorCssEntity) entity).getSelector());
        }
        else if(entity instanceof SelectorXPathEntity){
            selector = new SelectorXPath(((SelectorXPathEntity) entity).getSelector());
        }
        else {
            throw new IllegalArgumentException("Given entity type is not supported");
        }
        return selector;
    }

}
