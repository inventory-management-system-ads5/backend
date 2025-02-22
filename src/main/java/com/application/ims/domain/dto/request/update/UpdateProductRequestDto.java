package com.application.ims.domain.dto.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductRequestDto {

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
