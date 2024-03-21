package com.nttdata.bootcamp.mspersonalproduct.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Qualifier("customer-personal")
public class AppConfig {

    @Value("${config.base.endpoint.customer.personal}")
    private String baseUrlCustomerPersonal;

    @Value("${config.base.endpoint.product}")
    private String baseUrlProduct;

    @Value("${config.base.endpoint.customer.business}")
    private String baseUrlCustomerBusiness;

    @Bean
    @LoadBalanced
    @Qualifier("customer-personal")
    public WebClient.Builder registerWebClientPersonal() {
        System.out.println("========================================================================");
        System.out.println(baseUrlCustomerPersonal);
        return WebClient.builder().baseUrl(baseUrlCustomerPersonal);
    }

    @Bean
    @LoadBalanced
    @Qualifier("product")
    public WebClient.Builder registerWebClientProduct() {
        System.out.println("========================================================================");
        System.out.println(baseUrlProduct);
        return WebClient.builder().baseUrl(baseUrlProduct);
    }

    @Bean
    @LoadBalanced
    @Qualifier("customer-business")
    public WebClient.Builder registerWebClientCustomerBusiness() {

        return WebClient.builder().baseUrl(baseUrlCustomerBusiness);
    }
}
