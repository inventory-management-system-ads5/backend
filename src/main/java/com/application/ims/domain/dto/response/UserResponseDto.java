package com.application.ims.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String full_name;
    private String email;
    private String password;
    private Boolean is_admin;
    private Boolean is_active;

}