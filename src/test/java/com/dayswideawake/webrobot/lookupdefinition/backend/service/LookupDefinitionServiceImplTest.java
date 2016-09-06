package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;

@SpringBootTest
public class LookupDefinitionServiceImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private LookupDefinitionService lookupDefinitionService;
	@Autowired
	private LookupDefinitionRepository lookupDefinitionRepository;

	@AfterClass
	public void teardown() {
		lookupDefinitionRepository.deleteAll();
	}

	@Test
	public void shouldSaveAndGetLookupDefinition() throws MalformedURLException {
		Long accountId = 1L;
		URL url = UriComponentsBuilder.fromUriString("http://example.com").build().toUri().toURL();
		Site site = new Site.Builder(url).build();
		Selector selector = new SelectorCss("body>h1");
		Long intervalInSeconds = 10L;
		LookupDefinition lookupDefintion = new LookupDefinition.Builder(site, selector, intervalInSeconds).accountId(accountId).build();
		lookupDefinitionService.addLookupDefinition(lookupDefintion);
		Page<LookupDefinition> lookupDefintions = lookupDefinitionService.getLookupDefinitionsByAccountId(accountId, new PageRequest(0, 1000));
		assertEquals(lookupDefintions.getTotalElements(), 1);
	}

}
