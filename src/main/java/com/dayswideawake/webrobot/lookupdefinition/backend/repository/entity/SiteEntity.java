package com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SiteEntity {

    @Id
    @GeneratedValue
    private Long id;
    
}
