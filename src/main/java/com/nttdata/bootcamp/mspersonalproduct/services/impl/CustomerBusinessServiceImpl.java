package com.nttdata.bootcamp.mspersonalproduct.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.CustomerBusiness;
import com.nttdata.bootcamp.mspersonalproduct.services.CustomerBusinessService;

import reactor.core.publisher.Mono;

@Service
public class CustomerBusinessServiceImpl implements CustomerBusinessService {

    @Autowired
    @Qualifier("customer-business")
    private WebClient.Builder client;

    @Override
    public Mono<CustomerBusiness> findByNumberDocument(String numberDocument) {
        return client
                .build()
                .get().uri("/number-document/{numberDocument}", Map.of("numberDocument", numberDocument))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CustomerBusiness.class);
    }

}
