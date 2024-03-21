package com.nttdata.bootcamp.mspersonalproduct.services;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.PersonalProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalProductService {

    public Mono<PersonalProduct> save(PersonalProduct personalProduct);

    public Mono<PersonalProduct> saveAll(PersonalProduct personalProduct);

    public Flux<PersonalProduct> findByCustomerPersonalId(String customerPersonalId);

    public Mono<PersonalProduct> findByNumberAccount(String numberAccount);

    public Mono<PersonalProduct> findByProductIdAndCustomerPersonalId(String productId, String customerPersonalId);
}
