package com.application.ims.domain.dto.request.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @Min(value = 1, message = "A product must have at least 1 unit!")
    @NotNull
    private Integer stock_quantity;

    @Min(value = 0, message = "A product price must be a positive value!")
    @NotNull
    private Double unit_price;

    @NotNull
    private Long category_id;

    @NotNull
    private Long supplier_id;

}
