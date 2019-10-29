package br.com.star.wars.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="starwars")
public class StarWarsProperties {
	private String mongoHost="";
	private String swApiHost="https://swapi.co";

	public StarWarsProperties() {
	}

	public String getMongoHost() {
		return mongoHost;
	}

	public void setMongoHost(String mongoHost) {
		this.mongoHost = mongoHost;
	}

	public String getSwApiHost() {
		return swApiHost;
	}

	public void setSwApiHost(String swApiHost) {
		this.swApiHost = swApiHost;
	}
}
