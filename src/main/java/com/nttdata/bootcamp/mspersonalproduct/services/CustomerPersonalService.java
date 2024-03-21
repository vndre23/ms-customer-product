package com.nttdata.bootcamp.mspersonalproduct.services;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.CustomerPersonal;

import reactor.core.publisher.Mono;

public interface CustomerPersonalService {

    public Mono<CustomerPersonal> findByNumberDocument(String numberDocument);
}
