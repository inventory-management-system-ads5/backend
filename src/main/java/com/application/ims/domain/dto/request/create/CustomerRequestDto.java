package com.application.ims.domain.dto.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String contact_info;

}
