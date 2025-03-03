package com.application.ims.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemResponseDto {

    private Long id;
    private Integer quantity;
    private Double salePrice;
    private Long product_id;
    private Long sale_id;

}
