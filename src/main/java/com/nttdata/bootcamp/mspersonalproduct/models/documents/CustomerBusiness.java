package com.nttdata.bootcamp.mspersonalproduct.models.documents;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerBusiness {

    private String id;
    private String nameBusiness;
    private String numberDocument;
    private String address;
    private String phone;
    private Date createdAt;
}
