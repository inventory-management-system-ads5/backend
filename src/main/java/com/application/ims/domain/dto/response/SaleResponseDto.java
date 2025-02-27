package com.application.ims.domain.dto.response;

import com.application.ims.domain.dto.request.create.SaleRequestDto;
import com.application.ims.domain.enums.SaleStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private Double totalAmount;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    @NotNull
    private SaleStatus status;

    @NotNull
    private Long customer_id;

//    @NotNull
//    private List<SaleItemResponseDto> sale_items;
}
