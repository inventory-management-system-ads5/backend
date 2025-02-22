package com.application.ims.domain.dto.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddProductStockRequestDto {

    @Min(value = 1, message = "At least 1 product unit needs to be added in stock!")
    @NotNull
    private Integer stock_quantity;

}
