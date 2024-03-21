package com.nttdata.bootcamp.mspersonalproduct.services.impl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.BusinessProduct;
import com.nttdata.bootcamp.mspersonalproduct.repositories.BusinessProductRepository;
import com.nttdata.bootcamp.mspersonalproduct.services.BusinessProductService;

import reactor.core.publisher.Mono;

@Service
public class BusinessProductServiceImpl implements BusinessProductService {

    @Autowired
    private BusinessProductRepository businessProductRepository;

    @Override
    public Mono<BusinessProduct> createAccount(BusinessProduct businessProduct) {
        if (businessProduct.getCreateAt() == null) {
            businessProduct.setCreateAt(new Date());
        }
        if (businessProduct.getBalance() == null) {
            businessProduct.setBalance(0.0);
        }
        int min = 100;
        int max = 999;
        businessProduct.setNumberAccount(new Random().nextInt(max - min + 1) + min + "-"
                + new Random().nextInt(max - min + 1) + min + "-" + new Random().nextInt(max - min + 1) + min);
        return businessProductRepository.save(businessProduct);
    }

}
