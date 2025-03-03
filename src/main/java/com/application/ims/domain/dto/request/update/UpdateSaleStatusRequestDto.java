package com.application.ims.domain.dto.request.update;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import com.application.ims.domain.enums.SaleStatus;

@Data
@NoArgsConstructor
public class UpdateSaleStatusRequestDto {

    @NotNull
    private SaleStatus status;

}
