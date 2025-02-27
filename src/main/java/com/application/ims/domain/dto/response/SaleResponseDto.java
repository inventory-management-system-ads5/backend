package com.application.ims.domain.dto.response;

import com.application.ims.domain.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDto {

    private Long id;
    private Double total_amount;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private SaleStatus status;
    private Long customer_id;
    private List<SaleItemResponseDto> sale_items;

}
