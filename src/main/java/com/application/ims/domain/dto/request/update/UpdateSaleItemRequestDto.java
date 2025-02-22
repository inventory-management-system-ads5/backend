package com.application.ims.domain.dto.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSaleItemRequestDto {

    @Min(value = 1, message = "A SaleItem must have at least one of the product's quantity!")
    @NotNull
    private Integer quantity;

    @NotNull
    private Long product_id;

    private Long sale_id;
}
