package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
	private MediaType jsonContentType = MediaType.APPLICATION_JSON_UTF8;
	private MediaType halJsonContentType = new MediaType(MediaTypes.HAL_JSON, Charset.forName("utf-8"));
	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addLookupDefinitionShouldWork() throws Exception {
		AddLookupDefinitionRequest request = createAddLookupDefinitionRequest();
		String jsonRequest = toJson(request);
		RequestBuilder requestBuilder = post(LookupDefinitionUrls.BASE_URL).content(jsonRequest).contentType(jsonContentType);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated())
				.andExpect(content().contentType(halJsonContentType))
				.andExpect(jsonPath("$.content.accountId", is(request.getAccountId().intValue())))
				.andExpect(jsonPath("$.content.intervalInSeconds", is(request.getIntervalInSeconds().intValue())))
				.andExpect(jsonPath("$.content.selector.selector", is(request.getSelector().getSelector())))
				.andExpect(jsonPath("$.content.selector.selectorType", is(request.getSelector().getSelectorType().name())))
				.andExpect(jsonPath("$.content.site.url", is(request.getSite().getUrl())));
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
