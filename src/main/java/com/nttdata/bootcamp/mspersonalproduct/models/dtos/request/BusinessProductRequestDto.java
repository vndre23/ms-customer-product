package com.nttdata.bootcamp.mspersonalproduct.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessProductRequestDto {

    private String customerBusinessId;
    private String productId;
    private String businessNumberDocument;
}
