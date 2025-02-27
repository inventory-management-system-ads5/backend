package com.application.ims.domain.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

   @NotNull
    private Integer stock_quantity;

    @NotNull
    private Double unit_price;

    @NotNull
    private Long category_id;

    @NotNull
    private Long supplier_id;

}
