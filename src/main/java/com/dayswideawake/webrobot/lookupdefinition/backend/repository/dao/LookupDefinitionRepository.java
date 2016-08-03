package com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefinitionEntity;

@Repository
public interface LookupDefinitionRepository extends JpaRepository<LookupDefinitionEntity, Long> {

}
