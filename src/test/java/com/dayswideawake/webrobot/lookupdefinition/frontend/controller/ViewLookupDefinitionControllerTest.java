package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.MalformedURLException;
import java.net.URL;

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
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.SelectorXPath;
import com.dayswideawake.webrobot.lookupdefinition.backend.domain.Site;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SelectorTypeModel;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@SpringBootTest
public class ViewLookupDefinitionControllerTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private LookupDefinitionService lookupDefinitionService;
	private MediaType jsonContentType = MediaType.APPLICATION_JSON_UTF8;
	private MockMvc mockMvc;
	private LookupDefinition lookupDefinition;
	

	@BeforeClass
	public void setup() throws MalformedURLException {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		lookupDefinition = lookupDefinitionService.addLookupDefinition(getNewLookupDefinition());
	}

	@Test
	public void viewLookupDefinitionShouldWork() throws Exception {
		String resourceUrl = LookupDefinitionUrls.BASE_URL + "/" + lookupDefinition.getId();
		RequestBuilder requestBuilder = get(resourceUrl).accept(jsonContentType);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().contentType(jsonContentType))
				.andExpect(jsonPath("$.content.id", is(lookupDefinition.getId().intValue())))
				.andExpect(jsonPath("$.content.accountId", is(lookupDefinition.getAccountId().intValue())))
				.andExpect(jsonPath("$.content.intervalInSeconds", is(lookupDefinition.getIntervalInSeconds().intValue())))
				.andExpect(jsonPath("$.content.selector.selector", is(getSelectorJsonText(lookupDefinition.getSelector()))))
				.andExpect(jsonPath("$.content.selector.selectorType", is(getSelectorTypeJsonText(lookupDefinition.getSelector()))))
				.andExpect(jsonPath("$.content.site.url", is(lookupDefinition.getSite().getUrl().toString())));
		
	}
	
	private String getSelectorJsonText(Selector selectorDomain){
		String result = null;
		if(selectorDomain instanceof SelectorCss){
			result = ((SelectorCss) selectorDomain).getSelector();
		}
		else if(selectorDomain instanceof SelectorXPath){
			result = ((SelectorXPath) selectorDomain).getSelector();
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

	private LookupDefinition getNewLookupDefinition() throws MalformedURLException {
		Long accountId = 1L;
		Site site = new Site(new URL("http://example.com"));
		Selector selector = new SelectorCss("body");
		Long intervalInSeconds = 10L;
		return new LookupDefinition(accountId, site, selector, intervalInSeconds);
	}

}
