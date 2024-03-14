package com.example.qingyuanbackend.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("擎源后端管理服务器")
                        .description("擎源采用微服务架构模式，利用python-flask搭建了数据分析与解释服务，通过对前端传入的数据" +
                                "进行检测，并利用训练好的机器学习模型进行评估，实现检测服务。而springboot则主要对这些异常数据的状态进行维护" +
                                "同时对不同仓储区块、仓储维护人员、以及人员等级和其他信息进行维护与管理。")
                        .version("v1.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档")
                        .url("https://springshop.wiki.github.org/docs"));
    }

}