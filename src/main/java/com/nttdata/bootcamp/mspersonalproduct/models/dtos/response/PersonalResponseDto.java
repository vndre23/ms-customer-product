package com.nttdata.bootcamp.mspersonalproduct.models.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalResponseDto {

    private String id;
    private String numberDocument;
    private String name;
    private String lastname;
    private String address;
    private String phone;
    private List<PersonalProductResponseDto> personalProducts;
}
