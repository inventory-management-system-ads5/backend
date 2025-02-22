package com.application.ims.domain.dto.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {

    @NotNull
    private String full_name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private boolean is_admin;

}
