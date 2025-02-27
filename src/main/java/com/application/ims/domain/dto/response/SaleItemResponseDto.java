package com.application.ims.domain.dto.response;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double salePrice;

    @NotNull
    private Long product_id;

}
