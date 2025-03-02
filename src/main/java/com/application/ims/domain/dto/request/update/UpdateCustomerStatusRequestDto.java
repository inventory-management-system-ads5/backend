package com.application.ims.domain.dto.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCustomerStatusRequestDto {

    @NotNull
    private Boolean is_active;

}
