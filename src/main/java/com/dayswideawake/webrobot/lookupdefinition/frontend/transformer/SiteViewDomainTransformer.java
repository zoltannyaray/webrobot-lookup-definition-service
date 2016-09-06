package com.dayswideawake.webrobot.lookupdefinition.frontend.transformer;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddSiteRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SiteDetails;

@Component
public class SiteViewDomainTransformer {

    public Site postRequestToDomain(AddSiteRequest request){
        URL url;
        try {
            url = UriComponentsBuilder.fromUriString(request.getUrl()).build().toUri().toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Wrong url in site request", e);
        }
        return new Site.Builder(url).build();
    }
    
    public SiteDetails domainToDetails(Site domain){
        String url = domain.getUrl().toString();
        return new SiteDetails(url);
    }
    
}
