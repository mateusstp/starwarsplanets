package br.com.star.wars.config;/*
 * JUSTTO<br>
 *  Produto $JUSTTO APP - $${product_description}<br>
 *
 * Created: 10 2019, 28 <br>
 * <br>
 * All rights reserved.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * CLASS DOCUMENTATION <br>
 * ---------------------- <br>
 * PURPOSE: <br>
 * <br>
 * <br>
 * Development history: <br>
 * 10 2019, 28  - @author  Class creation. First implementation. <br>
 * <br>
 * <br>
 * Inner class: <br>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig  extends WebMvcConfigurationSupport {

    @Bean
    public Docket greetingApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.star.wars"))
                .build()
                .apiInfo(metaData());

    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("REST API Star Wars")
                .description("\"Spring Boot REST API for get planets from Start Wars\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}