package com.dayswideawake.webrobot.lookupdefinition;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SiteEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConversionServiceTest {

    @Autowired
    private ConversionService conversionService;
    
    @Test
    public void test() {
        SiteEntity siteEntity = new SiteEntity();
        SelectorEntity selectorEntity = new SelectorEntity();
        Long interval = 10L;
        Long lastLookupAt = new Date().getTime();
        LookupDefinitionEntity entity = new LookupDefinitionEntity(siteEntity, selectorEntity, interval, lastLookupAt);
        LookupDefinition domain = conversionService.convert(entity, LookupDefinition.class);
    }

}
