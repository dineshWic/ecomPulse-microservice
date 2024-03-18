package com.ecomPulse.orderservice.config;

import brave.Tracing;
import brave.http.HttpTracing;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final RestTemplateBuilder restTemplateBuilder;

    private Integer connectTimeout = 20000;
    private Integer readTimeout = 20000;

    @Bean
    public HttpTracing create(Tracing tracing) {
        return HttpTracing
                .newBuilder(tracing)
                .build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(HttpTracing httpTracing){
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .interceptors(TracingClientHttpRequestInterceptor.create(httpTracing))
                .build();
    }
}
