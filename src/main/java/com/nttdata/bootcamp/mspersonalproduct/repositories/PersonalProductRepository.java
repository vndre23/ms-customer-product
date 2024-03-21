package com.nttdata.bootcamp.mspersonalproduct.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.PersonalProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalProductRepository extends ReactiveMongoRepository<PersonalProduct, String> {

    public Flux<PersonalProduct> findByCustomerPersonalId(ObjectId customerPersonalId);

    public Mono<PersonalProduct> findByNumberAccount(String numberAccount);

    @Query("{'productId': ?0, 'customerPersonalId': ?1}")
    public Mono<PersonalProduct> findByProductIdAndCustomerPersonalId(ObjectId productId, ObjectId customerPersonalId);
}
