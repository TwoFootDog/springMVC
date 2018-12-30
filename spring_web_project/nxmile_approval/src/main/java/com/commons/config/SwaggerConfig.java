package com.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean // 필수항목
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)  // 기존적인 응답메시지 미사용
                .globalResponseMessage(RequestMethod.POST, getArrayList()) // getArrayList()함수에서 정의한 응답메시지 사용
                .apiInfo(getApiInfo())
                .select()                   // return ApiSelectoorBuilder(화면 관리)
                .apis(RequestHandlerSelectors.basePackage("com")) // Swagger를 적용할 패키지
                .paths(PathSelectors.any()) // url path 지정(예를들면 PathSelectors.ant("/home/**")인 경우 /home/ path를 가진 url만 공개하겠다는 의미. any인 경우 전체 url
                .build();                   // selector build
    }

    // 선택항목(Swagger UI에서 보여지는 정보)
    public ApiInfo getApiInfo() {
        return new ApiInfo("Service REST API Documentation",        // swagger 제목
                "REST Api Documentation",                     // swagger 설명
                "1.0",                                           // swaggeer 버전
                "localhost:8080",
                new Contact("kang-min-kyu","","aaa@gmail.com"), //작성자 정보
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

    // 선택항목(responseMessage 리스트를 별도로 생성.(defaultResponseMessage 미사용))
    private ArrayList<ResponseMessage> getArrayList() {
        ArrayList<ResponseMessage> lists = new ArrayList<ResponseMessage>();
        lists.add(new ResponseMessageBuilder().code(500).message("이상한요청").build());
        lists.add(new ResponseMessageBuilder().code(403).message("황당한요청").build());
        lists.add(new ResponseMessageBuilder().code(401).message("비인증된접근").build());
        return lists;
    }
}
