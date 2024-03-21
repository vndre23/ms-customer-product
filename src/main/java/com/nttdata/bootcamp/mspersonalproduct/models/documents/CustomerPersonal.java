package com.nttdata.bootcamp.mspersonalproduct.models.documents;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerPersonal {

    private String id;
    private String numberDocument;
    private String name;
    private String lastname;
    private String address;
    private String phone;
    private Date createAt;
}
