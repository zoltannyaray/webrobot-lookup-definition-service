package com.dayswideawake.webrobot.lookupdefinition.backend.domain;

import java.net.URL;

public class Site {

    private String name;
    private URL url;

    public Site(URL url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

}
