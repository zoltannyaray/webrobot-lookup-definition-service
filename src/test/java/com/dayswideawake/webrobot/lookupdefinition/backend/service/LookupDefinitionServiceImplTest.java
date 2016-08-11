package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;

@SpringBootTest
public class LookupDefinitionServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LookupDefinitionService lookupDefinitionService;

    @Test
    public void shouldSaveAndGetLookupDefinition() throws MalformedURLException {
        Long accountId = 1L;
        URL url = UriComponentsBuilder.fromUriString("http://example.com").build().toUri().toURL();
        Site site = new Site(url);
        Selector selector = new Selector();
        Long intervalInSeconds = 10L;
        LookupDefinition lookupDefintion = new LookupDefinition(site, selector, intervalInSeconds);
        lookupDefinitionService.addLookupDefinition(lookupDefintion);
        List<LookupDefinition> lookupDefintions = lookupDefinitionService.getLookupDefinitionsByAccountId(accountId);
        assertEquals(1, lookupDefintions.size());
    }

}
