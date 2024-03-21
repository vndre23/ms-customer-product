package com.nttdata.bootcamp.mspersonalproduct.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalProductRequestDto {

    private String customerPersonalId;
    private String productId;
    private String personalNumberDocument;

}
