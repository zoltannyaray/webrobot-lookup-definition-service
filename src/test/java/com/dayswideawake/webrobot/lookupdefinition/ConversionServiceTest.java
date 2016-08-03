package com.dayswideawake.webrobot.lookupdefinition;

import static org.testng.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SelectorEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.SiteEntity;

@SpringBootTest
public class ConversionServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ConversionService conversionService;
    
    @Test
    public void shouldConvertLookupDefinitionEntityToDomain() {
        assertTrue(conversionService.canConvert(LookupDefinitionEntity.class, LookupDefinition.class));
    }
    
    @Test
    public void shouldNotConvert(){
        assertFalse(conversionService.canConvert(Site.class, Selector.class));
    }
    
    @Test
    public void shouldConvertLookupDefinitionDomainToEntity(){
        assertTrue(conversionService.canConvert(LookupDefinition.class, LookupDefinitionEntity.class));
    }
    
    @Test
    public void shouldConvertSiteEntityToDomain() {
        assertTrue(conversionService.canConvert(SiteEntity.class, Site.class));
    }
    
    @Test
    public void shouldConvertSiteDomainToEntity(){
        assertTrue(conversionService.canConvert(Site.class, SiteEntity.class));
    }
    
    @Test
    public void shouldConvertSelectorEntityToDomain() {
        assertTrue(conversionService.canConvert(SelectorEntity.class, Selector.class));
    }
    
    @Test
    public void shouldConvertSelectorDomainToEntity(){
        assertTrue(conversionService.canConvert(Selector.class, SelectorEntity.class));
    }

}
