package com.fkczxt.selclassserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket creatRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.fkczxt.selclassserver.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("选课文档")
                .description("")
                .contact(new Contact("xxxx","http:localhost:8081/doc.html",""))
                .version("1.0")
                .build()
                ;
    }
    private List<SecurityScheme> securitySchemes(){
        List<SecurityScheme> result= new ArrayList<>();
        SecurityScheme apiKey=new ApiKey("Authorization","Authorization","Header");
        result.add(apiKey);
        return result;
    }
    private List<SecurityContext> securityContexts(){
        List<SecurityContext> result=new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }
    private SecurityContext getContextByPath(String pathRegex){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }
    private List<SecurityReference> defaultAuth(){
        List<SecurityReference> result=new ArrayList<>();
        AuthorizationScope authorizationScope=new AuthorizationScope("global",
                "accessEverything");
        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }
}
