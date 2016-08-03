package com.dayswideawake.webrobot.lookupdefinition.backend.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity.LookupDefintionEntity;

@Repository
public interface LookupDefinitionRepository extends JpaRepository<LookupDefintionEntity, Long> {

}
