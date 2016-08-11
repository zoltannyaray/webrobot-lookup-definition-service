package com.dayswideawake.webrobot.lookupdefinition.backend.repository.entity;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SiteEntity {

    @Id
    @GeneratedValue
    private Long id;
    private URL url;

    public SiteEntity() {
    }

    public SiteEntity(URL url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public URL getUrl() {
        return url;
    }

}
