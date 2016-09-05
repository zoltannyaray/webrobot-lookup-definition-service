package com.dayswideawake.webrobot.lookupdefinition.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayswideawake.webrobot.lookupdefinition.backend.service.LookupDefinitionService;
import com.dayswideawake.webrobot.lookupdefinition.frontend.model.ListLookupDefinitionsResponse;
import com.dayswideawake.webrobot.lookupdefinition.frontend.url.LookupDefinitionUrls;

@RestController
public class ListLookupDefinitionsController {

	private LookupDefinitionService lookupDefinitionService;

	@Autowired
	public ListLookupDefinitionsController(LookupDefinitionService lookupDefinitionService) {
		this.lookupDefinitionService = lookupDefinitionService;
	}
	
//	@RequestMapping(path=LookupDefinitionUrls.BASE_URL, method=RequestMethod.GET)
//	public ListLookupDefinitionsResponse list(){
//		
//	}
	
	
	
}
