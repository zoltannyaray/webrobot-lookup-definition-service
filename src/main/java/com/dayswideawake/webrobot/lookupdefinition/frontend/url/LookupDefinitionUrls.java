package com.dayswideawake.webrobot.lookupdefinition.frontend.url;

public class LookupDefinitionUrls {

	public static final String BASE_URL = "/lookup-definitions";
	public static final String PARAM_ACCOUNT_ID = "{accountId}";
	public static final String LIST_BY_ACCOUNT_ID = BASE_URL + "/by-account-id/" + PARAM_ACCOUNT_ID;
	
	public static final String getListByAccountIdUrl(Long accountId){
		return LIST_BY_ACCOUNT_ID.replace(PARAM_ACCOUNT_ID, String.valueOf(accountId));
	}
	
}
