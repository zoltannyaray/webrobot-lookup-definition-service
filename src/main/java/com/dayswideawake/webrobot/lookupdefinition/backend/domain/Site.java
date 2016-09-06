package com.dayswideawake.webrobot.lookupdefinition.backend.domain;

import java.net.URL;

public class Site {

	private String name;
	private URL url;

	private Site(Builder builder) {
		this.url = builder.url;
		this.name = builder.name;
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

	public static class Builder {
		private String name;
		private URL url;

		public Builder(URL url) {
			this.url = url;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Site build() {
			return new Site(this);
		}

	}

}
