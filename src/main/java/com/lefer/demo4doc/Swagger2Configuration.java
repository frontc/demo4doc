package com.lefer.demo4doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fang
 * @creatdate 17-7-28
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lefer.demo4doc.controller"))//要扫描的API(Controller)基础包
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("CDR在线文档")
                .contact("winning")
                .version("0.1")
                .build();
    }
}
