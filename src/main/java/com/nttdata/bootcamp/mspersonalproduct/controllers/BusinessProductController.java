package com.nttdata.bootcamp.mspersonalproduct.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.mspersonalproduct.models.documents.BusinessProduct;

import com.nttdata.bootcamp.mspersonalproduct.models.dtos.request.BusinessProductRequestDto;

import com.nttdata.bootcamp.mspersonalproduct.services.BusinessProductService;
import com.nttdata.bootcamp.mspersonalproduct.services.CustomerBusinessService;
import com.nttdata.bootcamp.mspersonalproduct.services.ProductService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/business-product")
public class BusinessProductController {

    @Autowired
    private BusinessProductService businessProductService;
    @Autowired
    private CustomerBusinessService customerBusinessService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public Mono<ResponseEntity<BusinessProduct>> save(@RequestBody BusinessProductRequestDto businessProduct) {
        BusinessProduct businessProductSaved = new BusinessProduct();
        return this.customerBusinessService.findByNumberDocument(businessProduct.getBusinessNumberDocument())
                .flatMap(business -> {
                    businessProductSaved.setCustomerBusinessId(new ObjectId(business.getId()));
                    businessProductSaved.setProductId(new ObjectId(businessProduct.getProductId()));
                    return this.productService.findById(businessProduct.getProductId());

                })
                .flatMap(product -> {
                    Mono<BusinessProduct> productMono;
                    businessProductSaved.setProductId(new ObjectId(product.getId()));
                    if (!product.getName().equalsIgnoreCase("Cuenta de Ahorros")) {
                        productMono = Mono.empty();
                        // productMono =
                        // this.businessProductService.createAccount(businessProductSaved);
                    }
                    if (!product.getName().equalsIgnoreCase("Plazo Fijo")) {
                        productMono = Mono.empty();
                        // productMono =
                        // this.businessProductService.createAccount(businessProductSaved);

                    } else {
                        productMono = this.businessProductService.createAccount(businessProductSaved);

                    }
                    return productMono;
                }).map(save -> ResponseEntity.status(HttpStatus.CREATED).body(save))
                .defaultIfEmpty(ResponseEntity.badRequest().build());

    }
}
