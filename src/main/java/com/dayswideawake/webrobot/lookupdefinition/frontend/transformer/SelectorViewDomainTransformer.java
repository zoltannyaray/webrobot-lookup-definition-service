package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorXPath;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddSelectorRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SelectorDetails;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SelectorTypeModel;

@Component
public class SelectorViewDomainTransformer {

    public Selector addRequestToDomain(AddSelectorRequest addSelectorRequest){
        Selector selector;
        if(addSelectorRequest.getSelectorType().equals(SelectorTypeModel.CSS)){
            selector = new SelectorCss(addSelectorRequest.getSelector());
        }
        else if(addSelectorRequest.getSelectorType().equals(SelectorTypeModel.XPATH)){
            selector = new SelectorXPath(addSelectorRequest.getSelector());
        }
        else {
            throw new IllegalArgumentException("Selector type is invalid: " + addSelectorRequest.getSelectorType());
        }
        return selector;
    }
    
    public SelectorDetails domainToDetails(Selector domain){
        SelectorTypeModel selectorType;
        String selector;
        if(domain instanceof SelectorCss){
            selectorType = SelectorTypeModel.CSS;
            selector = ((SelectorCss) domain).getSelector();
        }
        else if(domain instanceof SelectorXPath){
            selectorType = SelectorTypeModel.XPATH;
            selector = ((SelectorXPath) domain).getSelector();
        }
        else {
            throw new IllegalArgumentException("Domain selector type is invalid");
        }
        return new SelectorDetails(selector, selectorType);
    }
    
}
