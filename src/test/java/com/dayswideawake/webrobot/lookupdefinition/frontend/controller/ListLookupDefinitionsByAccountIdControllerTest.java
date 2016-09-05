package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@SpringBootTest
public class ListLookupDefinitionsByAccountIdControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private LookupDefinitionService lookupDefinitionService;
	private MediaType jsonContentType = MediaType.APPLICATION_JSON_UTF8;
	private MockMvc mockMvc;
	private List<LookupDefinition> testLookupDefinitions;

	@BeforeClass
	public void setup() throws MalformedURLException {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		testLookupDefinitions = createTestLookupDefinitions();
	}

	@Test
	public void listByAccountIdShouldListCorrectNumberOfItems() throws Exception{
		String url = LookupDefinitionUrls.getListByAccountIdUrl(2L);
		RequestBuilder requestBuilder = get(url).accept(jsonContentType);
		mockMvc.perform(requestBuilder)
			.andExpect(jsonPath("$.page.totalElements", is(10)));
	}
	
	private List<LookupDefinition> createTestLookupDefinitions() throws MalformedURLException{
		List<LookupDefinition> testLookupDefinitions = new ArrayList<>();
		for(int i = 0; i < 101; i++){
			Site site = new Site(new URL(String.format("http://site-%d.com", i)));
			Selector selector = new SelectorCss("body");
			Long intervalInSeconds = Long.valueOf(i + 10);
			Long accountId = Long.valueOf(i % 10 + 1);
			LookupDefinition testLookupDefinition = new LookupDefinition.Builder(site, selector, intervalInSeconds).accountId(accountId).build();
			testLookupDefinition = lookupDefinitionService.addLookupDefinition(testLookupDefinition);
			testLookupDefinitions.add(testLookupDefinition);
		}
		return testLookupDefinitions;
	}
	
}
