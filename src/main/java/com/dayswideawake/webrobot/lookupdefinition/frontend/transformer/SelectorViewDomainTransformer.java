package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SelectorPostRequest;

@Component
public class SelectorViewDomainTransformer {

    public Selector postRequestToDomain(SelectorPostRequest selectorPostRequest){
        return new Selector();
    }
    
}
