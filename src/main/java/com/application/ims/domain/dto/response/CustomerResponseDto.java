package com.application.ims.domain.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String contact_info;

//    @NotNull
//    private boolean isActive = true;

    //Vamos incluir as vendas?
//    @NotNull
//    private List<Sale> sales;
}
