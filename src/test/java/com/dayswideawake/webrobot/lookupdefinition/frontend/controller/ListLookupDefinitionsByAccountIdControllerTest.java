package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Selector;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorXPath;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SelectorTypeModel;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@SpringBootTest
public class ListLookupDefinitionsByAccountIdControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private LookupDefinitionService lookupDefinitionService;
	@Autowired
	private LookupDefinitionRepository lookupDefinitionRepository;
	private MediaType jsonContentType = MediaType.APPLICATION_JSON_UTF8;
	private MockMvc mockMvc;
	private List<LookupDefinition> testLookupDefinitions;

	@BeforeClass
	public void setup() throws MalformedURLException {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		testLookupDefinitions = createTestLookupDefinitions();
	}
	
	@AfterClass
	public void teardown(){
		lookupDefinitionRepository.deleteAll();
	}
	
	@Test
	public void listByAccountIdShouldListCorrectNumberOfTotalElements() throws Exception{
		String url = LookupDefinitionUrls.getListByAccountIdUrl(1L);
		RequestBuilder requestBuilder = get(url).accept(jsonContentType);
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(jsonContentType))
			.andExpect(jsonPath("$.page.totalElements", is(50)));
		url = LookupDefinitionUrls.getListByAccountIdUrl(2L);
		requestBuilder = get(url).accept(jsonContentType);
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(jsonContentType))
			.andExpect(jsonPath("$.page.totalElements", is(50)));
	}
	
	@Test
	public void listByAccountIdShouldListCorrect1stPageFor1stAccountId() throws Exception{
		String url = LookupDefinitionUrls.getListByAccountIdUrl(1L);
		RequestBuilder requestBuilder = get(url).accept(jsonContentType).param("page", "0").param("size", "10");;
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(jsonContentType))
			.andExpect(jsonPath("$.page.totalElements", is(50)))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].id", is(testLookupDefinitions.get(0).getId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].accountId", is(testLookupDefinitions.get(0).getAccountId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].intervalInSeconds", is(testLookupDefinitions.get(0).getIntervalInSeconds().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].site.url", is(testLookupDefinitions.get(0).getSite().getUrl().toString())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].selector.selector", is(getSelectorJsonText(testLookupDefinitions.get(0).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinitions.get(0).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].id", is(testLookupDefinitions.get(9).getId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].accountId", is(testLookupDefinitions.get(9).getAccountId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].intervalInSeconds", is(testLookupDefinitions.get(9).getIntervalInSeconds().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].site.url", is(testLookupDefinitions.get(9).getSite().getUrl().toString())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].selector.selector", is(getSelectorJsonText(testLookupDefinitions.get(9).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinitions.get(9).getSelector()))));
	}
	
	@Test
	public void listByAccountIdShouldListCorrect2ndPageFor1stAccountId() throws Exception{
		String url = LookupDefinitionUrls.getListByAccountIdUrl(1L);
		RequestBuilder requestBuilder = get(url).accept(jsonContentType).param("page", "1").param("size", "10");
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(jsonContentType))
			.andExpect(jsonPath("$.page.totalElements", is(50)))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].id", is(testLookupDefinitions.get(10).getId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].accountId", is(testLookupDefinitions.get(10).getAccountId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].intervalInSeconds", is(testLookupDefinitions.get(10).getIntervalInSeconds().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].site.url", is(testLookupDefinitions.get(10).getSite().getUrl().toString())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].selector.selector", is(getSelectorJsonText(testLookupDefinitions.get(10).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinitions.get(10).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].id", is(testLookupDefinitions.get(19).getId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].accountId", is(testLookupDefinitions.get(19).getAccountId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].intervalInSeconds", is(testLookupDefinitions.get(19).getIntervalInSeconds().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].site.url", is(testLookupDefinitions.get(19).getSite().getUrl().toString())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].selector.selector", is(getSelectorJsonText(testLookupDefinitions.get(19).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinitions.get(19).getSelector()))));
	}
	
	@Test
	public void listByAccountIdShouldListCorrect1stPageFor2ndAccountId() throws Exception{
		String url = LookupDefinitionUrls.getListByAccountIdUrl(2L);
		RequestBuilder requestBuilder = get(url).accept(jsonContentType).param("page", "0").param("size", "10");;
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(jsonContentType))
			.andExpect(jsonPath("$.page.totalElements", is(50)))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].id", is(testLookupDefinitions.get(50).getId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].accountId", is(testLookupDefinitions.get(50).getAccountId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].intervalInSeconds", is(testLookupDefinitions.get(50).getIntervalInSeconds().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].site.url", is(testLookupDefinitions.get(50).getSite().getUrl().toString())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].selector.selector", is(getSelectorJsonText(testLookupDefinitions.get(50).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinitions.get(50).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].id", is(testLookupDefinitions.get(59).getId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].accountId", is(testLookupDefinitions.get(59).getAccountId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].intervalInSeconds", is(testLookupDefinitions.get(59).getIntervalInSeconds().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].site.url", is(testLookupDefinitions.get(59).getSite().getUrl().toString())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].selector.selector", is(getSelectorJsonText(testLookupDefinitions.get(59).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinitions.get(59).getSelector()))));
	}
	
	@Test
	public void listByAccountIdShouldListCorrect2ndPageFor2ndAccountId() throws Exception{
		String url = LookupDefinitionUrls.getListByAccountIdUrl(2L);
		RequestBuilder requestBuilder = get(url).accept(jsonContentType).param("page", "1").param("size", "10");
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(jsonContentType))
			.andExpect(jsonPath("$.page.totalElements", is(50)))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].id", is(testLookupDefinitions.get(60).getId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].accountId", is(testLookupDefinitions.get(60).getAccountId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].intervalInSeconds", is(testLookupDefinitions.get(60).getIntervalInSeconds().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].site.url", is(testLookupDefinitions.get(60).getSite().getUrl().toString())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].selector.selector", is(getSelectorJsonText(testLookupDefinitions.get(60).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[0].selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinitions.get(60).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].id", is(testLookupDefinitions.get(69).getId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].accountId", is(testLookupDefinitions.get(69).getAccountId().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].intervalInSeconds", is(testLookupDefinitions.get(69).getIntervalInSeconds().intValue())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].site.url", is(testLookupDefinitions.get(69).getSite().getUrl().toString())))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].selector.selector", is(getSelectorJsonText(testLookupDefinitions.get(69).getSelector()))))
			.andExpect(jsonPath("$._embedded.lookupDefinitionDetailsList[9].selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinitions.get(69).getSelector()))));
	}
	
	private String getSelectorJsonText(Selector selector){
		String result = null;
		if(selector instanceof SelectorCss){
			result = ((SelectorCss) selector).getSelector();
		}
		else if(selector instanceof SelectorXPath){
			result = ((SelectorXPath) selector).getSelector();
		}
		return result;
	}
	
	private String getSelectorTypeJsonText(Selector selector){
		String result = null;
		if(selector instanceof SelectorCss){
			result = SelectorTypeModel.CSS.name();
		}
		else if(selector instanceof SelectorXPath){
			result = SelectorTypeModel.XPATH.name();
		}
		return result;
	}
	
	private List<LookupDefinition> createTestLookupDefinitions() throws MalformedURLException{
		List<LookupDefinition> testLookupDefinitions = new ArrayList<>();
		for(int i = 0; i < 100; i++){
			URL url = new URL(String.format("http://site-%d.com", i));
			Site site = new Site.Builder(url).build();
			Selector selector = new SelectorCss("body");
			Long intervalInSeconds = Long.valueOf(i + 10);
			Long accountId = Long.valueOf(i / 50 + 1);
			LookupDefinition testLookupDefinition = new LookupDefinition.Builder(site, selector, intervalInSeconds).accountId(accountId).build();
			testLookupDefinition = lookupDefinitionService.addLookupDefinition(testLookupDefinition);
			testLookupDefinitions.add(testLookupDefinition);
		}
		return testLookupDefinitions;
	}
	
}
