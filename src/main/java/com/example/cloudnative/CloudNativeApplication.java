package com.example.cloudnative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// 스프링 콘텍스트에 설정 데이터 빈을 로드한다.
@ConfigurationPropertiesScan
public class CloudNativeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudNativeApplication.class, args);
    }
}
