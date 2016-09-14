package com.dayswideawake.webrobot.lookupdefinition.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

	String CHANNEL_OUTPUT_NEW_LOOKUP_DEFINITIONS = "new-lookup-definitions";
	
	@Output(CHANNEL_OUTPUT_NEW_LOOKUP_DEFINITIONS)
	MessageChannel newLookupDefinitions();
	
}
