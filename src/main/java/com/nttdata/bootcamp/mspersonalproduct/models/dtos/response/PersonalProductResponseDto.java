package com.nttdata.bootcamp.mspersonalproduct.models.dtos.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalProductResponseDto {

    private String numberAccount;
    private Double balance;
    private Date createAt;
    private ProductResponseDto product;
}
