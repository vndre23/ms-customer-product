package com.nttdata.bootcamp.mspersonalproduct.controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nttdata.bootcamp.mspersonalproduct.models.documents.PersonalProduct;

import com.nttdata.bootcamp.mspersonalproduct.models.dtos.request.PersonalProductRequestDto;
import com.nttdata.bootcamp.mspersonalproduct.models.dtos.request.UpdateBalanceDto;
import com.nttdata.bootcamp.mspersonalproduct.models.dtos.response.PersonalProductResponseDto;
import com.nttdata.bootcamp.mspersonalproduct.models.dtos.response.PersonalResponseDto;
import com.nttdata.bootcamp.mspersonalproduct.models.dtos.response.ProductResponseDto;
import com.nttdata.bootcamp.mspersonalproduct.services.CustomerPersonalService;
import com.nttdata.bootcamp.mspersonalproduct.services.PersonalProductService;
import com.nttdata.bootcamp.mspersonalproduct.services.ProductService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/personal-product")
@AllArgsConstructor
public class PersonalProductController { // actua como un handler

    private PersonalProductService personalProductService;

    private CustomerPersonalService customerPersonalService;

    private ProductService productService;

    @PostMapping
    public Mono<ResponseEntity<PersonalProductRequestDto>> save(
            @RequestBody PersonalProductRequestDto personalProduct) {
        PersonalProduct personalProductSaved = new PersonalProduct();
        return this.customerPersonalService.findByNumberDocument(personalProduct.getPersonalNumberDocument())

                .flatMap(personal -> {
                    personalProductSaved.setCustomerPersonalId(new ObjectId(personal.getId()));
                    personalProduct.setCustomerPersonalId(new ObjectId(personal.getId()).toString());

                    return productService.findById(personalProduct.getProductId());

                })
                .flatMap(product -> {
                    personalProductSaved.setProductId(new ObjectId(personalProduct.getProductId()));
                    personalProductSaved.setRemainingMovesLimit(product.getMovementLimit());
                    return this.personalProductService
                            .findByProductIdAndCustomerPersonalId(product.getId(),
                                    personalProductSaved.getCustomerPersonalId().toString())
                            .flatMap(exist -> {
                                return Mono.error(new IllegalArgumentException("personal with product"));
                            })
                            .switchIfEmpty(personalProductService.save(personalProductSaved));
                })
                .map(save -> ResponseEntity.status(HttpStatus.CREATED).body(personalProduct));
    }

    @GetMapping("/customer/{numberDocument}")
    public Mono<ResponseEntity<PersonalResponseDto>> findByNumberDocument(@PathVariable String numberDocument) {
        PersonalResponseDto responseDto = new PersonalResponseDto();
        List<PersonalProductResponseDto> personalProducts = new ArrayList<>();

        return this.customerPersonalService.findByNumberDocument(numberDocument).flatMap(personal -> {
            responseDto.setName(personal.getName());
            responseDto.setLastname(personal.getLastname());
            responseDto.setNumberDocument(personal.getNumberDocument());
            responseDto.setAddress(personal.getAddress());
            responseDto.setPhone(personal.getPhone());
            responseDto.setId(personal.getId());

            return this.personalProductService.findByCustomerPersonalId(responseDto.getId()).collectList()
                    .map(personalProductList -> {

                        personalProductList.forEach(pp -> {

                            PersonalProductResponseDto personalP = new PersonalProductResponseDto();
                            personalP.setBalance(pp.getBalance());
                            personalP.setNumberAccount(pp.getNumberAccount());
                            personalP.setCreateAt(pp.getCreateAt());
                            System.out.println("========>>>>>> " + pp.getProductId().toString());
                            // this.productService.findById("65f8e60a9f150a6435b24a34"/*pp.getProductId().toString()*/)
                            this.productService.findById(pp.getProductId().toString())
                                    .map(product -> {
                                        System.out.println("========" + personalP.toString());
                                        System.out.println("========" + product.toString());
                                        ProductResponseDto p = new ProductResponseDto();
                                        p.setName(product.getName());
                                        p.setCommission(product.getCommission());
                                        p.setMovementLimit(product.getMovementLimit());
                                        p.setCreatedAt(product.getCreatedAt());
                                        personalP.setProduct(p);
                                        personalProducts.add(personalP);
                                        responseDto.setPersonalProducts(personalProducts);
                                        return product;
                                    }).block();
                        });

                        return personalProductList;
                    });
        })
                .map(response -> ResponseEntity.ok(responseDto))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/number-account/{numberAccount}")
    public Mono<ResponseEntity<PersonalProduct>> findByNumberAccount(@PathVariable String numberAccount) {
        return this.personalProductService.findByNumberAccount(numberAccount)
                .map(find -> ResponseEntity.ok(find))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/add-balance/{numberAccount}")
    public Mono<ResponseEntity<PersonalProduct>> updateBalance(@PathVariable String numberAccount,
            @RequestBody UpdateBalanceDto dto) {
        return this.personalProductService.findByNumberAccount(numberAccount)
                .flatMap(personalP -> {
                    personalP.setBalance(dto.getAmount());
                    return this.personalProductService.saveAll(personalP);
                })
                .map(saved -> ResponseEntity.ok().body(saved))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer-personal/{customerPersonalId}")
    public Mono<ResponseEntity<Flux<PersonalProduct>>> findByCustomerPersonalId(
            @PathVariable String customerPersonalId) {
        return Mono.just(ResponseEntity.ok(this.personalProductService.findByCustomerPersonalId(customerPersonalId)));
    }

}
