package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
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
	private LookupDefinition testLookupDefinition;
	

	@BeforeClass
	public void setup() throws MalformedURLException {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		testLookupDefinition = lookupDefinitionService.addLookupDefinition(getNewLookupDefinition());
	}

	@Test
	public void viewLookupDefinitionShouldWork() throws Exception {
		String resourceUrl = LookupDefinitionUrls.BASE_URL + "/" + testLookupDefinition.getId();
		RequestBuilder requestBuilder = get(resourceUrl).accept(jsonContentType);
		Link expectedSelfLink = linkTo(methodOn(ViewLookupDefinitionController.class).view(testLookupDefinition.getId())).withSelfRel();
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().contentType(jsonContentType))
				.andExpect(jsonPath("$.id", is(testLookupDefinition.getId().intValue())))
				.andExpect(jsonPath("$.accountId", is(testLookupDefinition.getAccountId().intValue())))
				.andExpect(jsonPath("$.intervalInSeconds", is(testLookupDefinition.getIntervalInSeconds().intValue())))
				.andExpect(jsonPath("$.selector.selector", is(getSelectorJsonText(testLookupDefinition.getSelector()))))
				.andExpect(jsonPath("$.selector.selectorType", is(getSelectorTypeJsonText(testLookupDefinition.getSelector()))))
				.andExpect(jsonPath("$.site.url", is(testLookupDefinition.getSite().getUrl().toString())))
				.andExpect(jsonPath("$._links.self.href", is(expectedSelfLink.getHref())));
	}
	
	@Test
	public void viewLookupDefinitionShouldReturn404OnNotExistingResource() throws Exception{
		String resourceUrl = LookupDefinitionUrls.BASE_URL + "/1000";
		RequestBuilder requestBuilder = get(resourceUrl).accept(jsonContentType);
		mockMvc.perform(requestBuilder)
			.andExpect(status().isNotFound());
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

	private LookupDefinition getNewLookupDefinition() throws MalformedURLException {
		Long accountId = 1L;
		Site site = new Site(new URL("http://example.com"));
		Selector selector = new SelectorCss("body");
		Long intervalInSeconds = 10L;
		return new LookupDefinition.Builder(site, selector, intervalInSeconds).accountId(accountId).build();
	}

}
