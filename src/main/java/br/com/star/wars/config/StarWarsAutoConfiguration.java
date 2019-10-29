package br.com.star.wars.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableMongoRepositories({"br.com.star.wars.repository"})
@EnableConfigurationProperties({StarWarsProperties.class})
@EnableAutoConfiguration
@EnableWebMvc
@EnableSwagger2
@ComponentScan({"br.com.star.wars"})
@EnableAspectJAutoProxy
public class StarWarsAutoConfiguration {

}
