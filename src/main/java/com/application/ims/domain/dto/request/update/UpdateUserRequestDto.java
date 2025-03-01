package com.application.ims.domain.dto.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserRequestDto {

    @NotNull
    private String full_name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Boolean is_admin;

}
