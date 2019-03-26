package com.kakao.bank.test.mapsearch.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestTemplateAuthInterceptor implements RequestInterceptor {
    @Value("${constants.kakao.auth}")
    private String authKey;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", String.join(" ", "KakaoAK", authKey));
    }
}
