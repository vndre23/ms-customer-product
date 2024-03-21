package com.nttdata.bootcamp.mspersonalproduct.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.BusinessProduct;

public interface BusinessProductRepository extends ReactiveMongoRepository<BusinessProduct, String> {

}
