package com.nttdata.bootcamp.mspersonalproduct.models.documents;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "customers_products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PersonalProduct {

    @Id
    private String id;
    private ObjectId customerPersonalId;
    private ObjectId productId;
    private String numberAccount;
    private Double balance;
    private Date createAt;
    private Double remainingMovesLimit;
}
