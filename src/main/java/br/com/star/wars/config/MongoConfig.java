package br.com.star.wars.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

	@Autowired
	StarWarsProperties properties;
	@Bean
	public MongoClient mongo() {
		return new MongoClient(properties.getMongoHost());
	}
}
