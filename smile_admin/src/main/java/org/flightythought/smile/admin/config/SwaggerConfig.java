package org.flightythought.smile.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${server.port}")
    private String serverPort;

    /**
     * 配置Swagger API文档
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.flightythought.smile.admin.controller"))
                .paths(PathSelectors.any())
                .build().securitySchemes(securitySchemes()).securityContexts(securityContexts());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Smile APP Admin System API")
                .description("笑美APP 后台管理系统： http://localhost:" + serverPort
                        + "/<br/>Copyright &copy; 2018~" + Year.now().getValue())
//                .termsOfServiceUrl("http://localhost:" + serverPort + "/")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> result = new ArrayList<>();
        result.add(new ApiKey("Authorization", "Authorization", "header"));
        return result;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> result = new ArrayList<>();
        result.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build());
        return result;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> result = new ArrayList<>();
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }
}
