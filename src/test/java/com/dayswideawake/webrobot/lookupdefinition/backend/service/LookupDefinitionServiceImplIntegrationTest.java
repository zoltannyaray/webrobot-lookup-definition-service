package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.Message;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookupdefinition.TestGroup;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.messaging.Channels;
import com.dayswideawake.webrobot.lookupdefinition.messaging.model.LookupDefinitionCreatedEventMessage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Test(groups=TestGroup.GROUP_INTEGRATION)
public class LookupDefinitionServiceImplIntegrationTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private LookupDefinitionService lookupDefinitionService;
	@Autowired
	private LookupDefinitionRepository lookupDefinitionRepository;
	@Autowired
	private Channels channels;
	@Autowired
	private MessageCollector messageCollector;
	@Autowired
	private ObjectMapper mapper;

	@BeforeMethod
	public void setup() {
		messageCollector.forChannel(channels.newLookupDefinitions()).clear();
		lookupDefinitionRepository.deleteAll();
	}

	public void shouldSaveAndGetLookupDefinition() throws JsonParseException, JsonMappingException, IOException {
		Long accountId = 1L;
		URL url = UriComponentsBuilder.fromUriString("http://example.com").build().toUri().toURL();
		Site site = new Site.Builder(url).build();
		Selector selector = new SelectorCss("body>h1");
		Long intervalInSeconds = 10L;
		LookupDefinition lookupDefintion = new LookupDefinition.Builder(site, selector, intervalInSeconds).accountId(accountId).build();
		LookupDefinition createdLookupDefinition = lookupDefinitionService.addLookupDefinition(lookupDefintion);
		Page<LookupDefinition> lookupDefintions = lookupDefinitionService.getLookupDefinitionsByAccountId(accountId, new PageRequest(0, 1000));
		Assert.assertEquals(lookupDefintions.getTotalElements(), 1);
		Message<String> message = (Message<String>) messageCollector.forChannel(channels.newLookupDefinitions()).poll();
		Assert.assertNotNull(message);
		LookupDefinitionCreatedEventMessage messagePayload = mapper.readValue(message.getPayload(), LookupDefinitionCreatedEventMessage.class);
		Assert.assertEquals(messagePayload.getAccountId(), accountId);
		Assert.assertEquals(messagePayload.getIntervalInSeconds(), intervalInSeconds);
		Assert.assertEquals(messagePayload.getLookupDefinitionId(), createdLookupDefinition.getId());
	}

	public void shouldReturnCorrectNumberOfLookupDefinitionsForSchedule() throws MalformedURLException {
		addTestLookupDefinitionsForSchedule();
		PageRequest pageRequest = new PageRequest(0, 100);
		Page<LookupDefinition> lookupDefinitionsForSchedule = lookupDefinitionService.getLookupDefinitionsForSchedule(pageRequest);
		Assert.assertEquals(lookupDefinitionsForSchedule.getTotalElements(), 6);
	}

	private void addTestLookupDefinitionsForSchedule() throws MalformedURLException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -100);
		for (int i = 0; i < 10; i++) {
			URL url = new URL("http://example.com");
			Site site = new Site.Builder(url).build();
			Selector selector = new SelectorCss("body");
			Long intervalInSeconds = 70L;
			LookupDefinition.Builder lookupDefinitionBuilder = new LookupDefinition.Builder(site, selector, intervalInSeconds);
			if (i < 8) {
				Date lastLookupAt = calendar.getTime();
				lookupDefinitionBuilder.lastLookupAt(lastLookupAt);
			}
			LookupDefinition lookupDefinition = lookupDefinitionBuilder.build();
			lookupDefinitionService.addLookupDefinition(lookupDefinition);
			calendar.add(Calendar.SECOND, 10);
		}
	}

}
