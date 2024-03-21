package com.nttdata.bootcamp.mspersonalproduct.services;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.BusinessProduct;

import reactor.core.publisher.Mono;

public interface BusinessProductService {
    public Mono<BusinessProduct> createAccount(BusinessProduct businessProduct);

}
