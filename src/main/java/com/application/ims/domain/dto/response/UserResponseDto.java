package com.application.ims.domain.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private String full_name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private boolean is_admin;

//    @NotNull
//    private boolean isActive;

}
