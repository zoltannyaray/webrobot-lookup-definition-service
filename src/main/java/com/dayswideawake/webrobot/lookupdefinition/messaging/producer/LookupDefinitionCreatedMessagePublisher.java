package com.dayswideawake.webrobot.lookupdefinition.messaging.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookupdefinition.backend.event.LookupDefinitionCreatedEvent;
import com.dayswideawake.webrobot.lookupdefinition.messaging.Channels;
import com.dayswideawake.webrobot.lookupdefinition.messaging.model.LookupDefinitionCreatedEventMessage;
import com.dayswideawake.webrobot.lookupdefinition.messaging.transformer.LookupDefinitionDomainMessageTransformer;

@Component
public class LookupDefinitionCreatedMessagePublisher {

	private LookupDefinitionDomainMessageTransformer domainMessageTransformer;
	private Channels channels;

	@Autowired
	public LookupDefinitionCreatedMessagePublisher(Channels channels, LookupDefinitionDomainMessageTransformer domainMessageTransformer) {
		this.channels = channels;
		this.domainMessageTransformer = domainMessageTransformer;
	}

	@EventListener
	private void publishLookupDefinitionCreatedEvent(LookupDefinitionCreatedEvent domainEvent) {
		LookupDefinitionCreatedEventMessage messagePayload = domainMessageTransformer.eventToMessage(domainEvent);
		Message<LookupDefinitionCreatedEventMessage> message = MessageBuilder.withPayload(messagePayload).build();
		channels.newLookupDefinitions().send(message);
	}

}
