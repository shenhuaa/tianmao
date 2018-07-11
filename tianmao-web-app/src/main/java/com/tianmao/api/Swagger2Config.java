package com.tianmao.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2 API文档配置
 *
 * @author roach
 * @date 2018/03/06
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket mallDocket() {
        return docket(ApiMallGroup.class, "商城api文档");
    }

    @Bean
    public Docket walletDocket() {
        return docket(ApiWalletGroup.class, "钱包api文档");
    }

    private Docket docket(final Class<? extends Annotation> annotation, String groupName) {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder
                .name("debug")
                .required(true)
                .modelRef(new ModelRef("boolean"))
                .parameterType("query")
                .defaultValue("true")
                .description("调式放行")
                .build();

        //默认请求参数
        List<Parameter> defaultParameters = new ArrayList<>();
        defaultParameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(annotation))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)//默认response状态码
                .globalOperationParameters(defaultParameters());
    }

    /**
     * 构建 api文档的详细信息函数
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("爱士多app文档")
                //.contact(new Contact("istore", AppContext.appUrl + "/swagger-ui.html", "istore"))
                //.description("爱士多商城文档1.0")
                .version("1.0")
                .build();
    }

    /**
     * 默认请求参数
     *
     * @return
     */
    private List<Parameter> defaultParameters() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder
                .name("debug")
                .required(true)
                .modelRef(new ModelRef("String"))
                .parameterType("query")
                .defaultValue("true")
                .description("调式放行")
                .build();
        List<Parameter> defaultParameters = new ArrayList<>();
        defaultParameters.add(parameterBuilder.build());
        return defaultParameters;
    }
}