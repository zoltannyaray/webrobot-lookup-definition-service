package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LookupDefinitionServiceImplTest {

    @Autowired
    private LookupDefinitionService lookupDefinitionService;
    
    @Test
    public void test() {
        Long accountId = 1L;
        Site site = new Site();
        Selector selector = new Selector();
        Long intervalInSeconds = 10L;
        Date lastLookupAt = new Date();
        LookupDefinition lookupDefintion = new LookupDefinition(site, selector, intervalInSeconds, lastLookupAt);
        lookupDefinitionService.addLookupDefinition(lookupDefintion);
        List<LookupDefinition> lookupDefintions = lookupDefinitionService.getLookupDefinitionsByAccountId(accountId);
        assertEquals(1, lookupDefintions.size());
    }

}
