package com.example.cloudnative.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// 이 클래스는 polar로 시작하는 설정 속성에 대한 소스임을 표시
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    // 사용자 정의 속성인 polar.greeting(프리픽스 + 필드명) 속성이 문자열로 인식되는 필드
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void PolarProperties(String greeting) {
        this.greeting = greeting;
    }
}
