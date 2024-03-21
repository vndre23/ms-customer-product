package com.nttdata.bootcamp.mspersonalproduct.services;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.CustomerBusiness;

import reactor.core.publisher.Mono;

public interface CustomerBusinessService {
    public Mono<CustomerBusiness> findByNumberDocument(String numberDocument);

}
