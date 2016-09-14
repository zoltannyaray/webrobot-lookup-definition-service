package com.dayswideawake.webrobot.lookupdefinition.backend.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookupdefinition.backend.domain.LookupDefinition;
import com.dayswideawake.webrobot.lookupdefinition.backend.event.LookupDefinitionCreatedEvent;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao.LookupDefinitionRepository;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.QLookupDefinitionEntity;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer.LookupDefinitionDomainEntityTransformer;
import com.dayswideawake.webrobot.lookupdefinition.backend.service.transformer.LookupDefinitionDomainEventTransformer;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class LookupDefinitionServiceImpl implements LookupDefinitionService {

	private LookupDefinitionRepository repository;
	private LookupDefinitionDomainEntityTransformer domainEntityTransformer;
	private ApplicationEventPublisher applicationEventPublisher;
	private LookupDefinitionDomainEventTransformer domainEventTransformer;

	@Autowired
	public LookupDefinitionServiceImpl(LookupDefinitionRepository repository, LookupDefinitionDomainEntityTransformer domainEntityTransformer, LookupDefinitionDomainEventTransformer domainEventTransformer, ApplicationEventPublisher applicationEventPublisher) {
		this.repository = repository;
		this.domainEntityTransformer = domainEntityTransformer;
		this.domainEventTransformer = domainEventTransformer;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public Optional<LookupDefinition> getLookupDefinitionById(Long id) {
		LookupDefinitionEntity entity = repository.findOne(id);
		LookupDefinition lookupDefinition = null;
		if (entity != null) {
			lookupDefinition = domainEntityTransformer.entityToDomain(entity);
		}
		return Optional.ofNullable(lookupDefinition);
	}

	@Override
	public LookupDefinition addLookupDefinition(LookupDefinition lookupDefinition) {
		LookupDefinitionEntity entity = domainEntityTransformer.domainToEntity(lookupDefinition);
		entity = repository.save(entity);
		LookupDefinition savedLookupDefinition = domainEntityTransformer.entityToDomain(entity);
		LookupDefinitionCreatedEvent lookupDefinitionCreatedEvent = domainEventTransformer.domainToCreatedEvent(savedLookupDefinition);
		applicationEventPublisher.publishEvent(lookupDefinitionCreatedEvent);
		return savedLookupDefinition;
	}

	@Override
	public Page<LookupDefinition> getLookupDefinitionsByAccountId(Long accountId, Pageable pageable) {
		Page<LookupDefinitionEntity> entityPage = repository.findByAccountId(accountId, pageable);
		return entityPage.map(domainEntityTransformer);
	}

	@Override
	public Page<LookupDefinition> getLookupDefinitionsForSchedule(Pageable pageable) {
		QLookupDefinitionEntity lookupDefinition = QLookupDefinitionEntity.lookupDefinitionEntity;
		Long currentTimeStamp = new Date().getTime();
		BooleanExpression lastLookupWasMoreThanIntervalSecondsAgo = lookupDefinition.lastLookupAt.add(lookupDefinition.intervalInSeconds.multiply(1000)).loe(currentTimeStamp);
		BooleanExpression lookupHasNotBeenMadeYet = lookupDefinition.lastLookupAt.isNull();
		BooleanExpression needToSchedule = lookupHasNotBeenMadeYet.or(lastLookupWasMoreThanIntervalSecondsAgo);
		Page<LookupDefinitionEntity> entityPage = repository.findAll(needToSchedule, pageable);
		return entityPage.map(domainEntityTransformer);
	}

}
