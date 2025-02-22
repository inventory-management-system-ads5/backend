package com.application.ims.domain.dto.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSupplierRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String contact_info;

}
