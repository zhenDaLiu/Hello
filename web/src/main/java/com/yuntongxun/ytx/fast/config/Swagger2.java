package com.yuntongxun.ytx.fast.config;

import com.yuntongxun.ytx.constants.YtxConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.yuntongxun.ytx.fast.constenum.ConstStr;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: luocc
 * @Date: 2018/9/13
 * @Description: Swagger Config
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger", value = { "enable" }, havingValue = "true")
public class Swagger2 {

    /**
     * 客户端 controller接口路径
     */
    private String CLIENT_CONTROLLER_PATH = "com.yuntongxun.ytx.controller";
    /**
     * 后台管理网站 controller接口路径
     */
    private String ADMIN_CONTROLLER_PATH = "com.yuntongxun.admin.controller";

    @Bean
    public Docket createRestApi() {
        final List<ResponseMessage> responseMessageList = responseMessage();

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .pathMapping("/")
                .host("127.0.0.1:8080")
                .apiInfo(clientApiInfo())
                .groupName("client")
                .select()
                .apis(RequestHandlerSelectors.basePackage(CLIENT_CONTROLLER_PATH))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getGlobalParameter());
    }


    /**
     * 后台管理网站
     * @return
     */
    @Bean
    public Docket adminRestApi() {
        final List<ResponseMessage> responseMessageList = responseMessage();
        List<Parameter> globalParameter = getGlobalParameter();
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name(ConstStr.LOGIN_TYPE).description("登录类型:值为admin").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        globalParameter.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .pathMapping("/")
                .host("127.0.0.1:8080")
                .apiInfo(adminApiInfo())
                .groupName("admin")
                .select()
                .apis(RequestHandlerSelectors.basePackage(ADMIN_CONTROLLER_PATH))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(globalParameter);
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title(YtxConstants.PROJECT_NAME + "API文档")
                .description("后台管理网站文档")
                .version("1.0")
                .build();
    }

    private ApiInfo clientApiInfo() {
        return new ApiInfoBuilder()
                .title(YtxConstants.PROJECT_NAME + "API文档")
                .description("客户端接口文档")
                .version("1.0")
                .build();
    }

    private List<Parameter> getGlobalParameter(){
        // 全局Access-Token参数添加
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameterBuilder.name(ConstStr.ACCESS_TOKEN).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(parameterBuilder.build());
        return parameters;


    }

    private List<ResponseMessage> responseMessage(){
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到资源").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("参数错误").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("未登录").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("权限不足").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("未找到资源").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(405).message("请求方法不对").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(600).message("业务异常，meta--->msg为错误提示信息").responseModel(new ModelRef("ApiError")).build());
        return responseMessageList;
    }
}

