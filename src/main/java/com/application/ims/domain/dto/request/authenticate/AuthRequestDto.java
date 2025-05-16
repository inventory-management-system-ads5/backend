package com.application.ims.domain.dto.request.authenticate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequestDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
