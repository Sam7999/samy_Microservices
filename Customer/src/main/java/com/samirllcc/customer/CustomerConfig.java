package com.samirllcc.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {
    @Bean
    @LoadBalanced // when we have 2 instances of a microservice we add this @ so when making a hhtp request it chooses one of them and it doesn't get confused

    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
