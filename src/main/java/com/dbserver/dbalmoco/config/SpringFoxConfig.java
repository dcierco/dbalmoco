package com.dbserver.dbalmoco.config;

import java.util.ArrayList;

import com.dbserver.dbalmoco.DbalmocoApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        final String basePackageApi = DbalmocoApplication.class.getPackage().getName();
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(basePackageApi))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        ApiInfo apiInfo = new ApiInfo("DBSAlmoço",
        "Sistema para votação de restaurantes para almoço dos funcionários da db",
        "0.1 (Alpha Release)",
        null,
        new Contact("Daniel Cierco", "https://github.com/dcierco", "daniel_cierco@live.com"), null, null, new ArrayList<>());

        return apiInfo;
    }
}
