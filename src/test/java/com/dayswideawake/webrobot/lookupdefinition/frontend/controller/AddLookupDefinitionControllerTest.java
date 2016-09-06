package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddLookupDefinitionRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddSelectorRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.AddSiteRequest;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.SelectorTypeModel;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@SpringBootTest
public class AddLookupDefinitionControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	@Autowired
	private LookupDefinitionRepository lookupDefinitionRepository;
	private MediaType jsonContentType = MediaType.APPLICATION_JSON_UTF8;
	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@AfterClass
	public void teardown(){
		lookupDefinitionRepository.deleteAll();
	}

	@Test
	public void addLookupDefinitionShouldWork() throws Exception {
		AddLookupDefinitionRequest request = createAddLookupDefinitionRequest();
		String jsonRequest = toJson(request);
		RequestBuilder requestBuilder = post(LookupDefinitionUrls.BASE_URL).content(jsonRequest).contentType(jsonContentType).accept(jsonContentType);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(jsonContentType))
				.andExpect(jsonPath("$.accountId", is(request.getAccountId().intValue())))
				.andExpect(jsonPath("$.intervalInSeconds", is(request.getIntervalInSeconds().intValue())))
				.andExpect(jsonPath("$.selector.selector", is(request.getSelector().getSelector())))
				.andExpect(jsonPath("$.selector.selectorType", is(request.getSelector().getSelectorType().name())))
				.andExpect(jsonPath("$.site.url", is(request.getSite().getUrl())));
	}

	private AddLookupDefinitionRequest createAddLookupDefinitionRequest() {
		Long accountId = 1L;
		AddSiteRequest site = new AddSiteRequest("http://example.com");
		AddSelectorRequest selector = new AddSelectorRequest("body", SelectorTypeModel.CSS);
		Long intervalInSeconds = 10L;
		return new AddLookupDefinitionRequest(accountId, site, selector, intervalInSeconds);
	}

	private String toJson(Object object) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(object, jsonContentType, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
