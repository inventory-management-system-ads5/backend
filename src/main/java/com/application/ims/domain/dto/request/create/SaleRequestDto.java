package com.application.ims.domain.dto.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SaleRequestDto {

    @NotNull
    private Long customer_id;

    @NotNull
    private List<SaleItemRequestDto> sale_items;
}
