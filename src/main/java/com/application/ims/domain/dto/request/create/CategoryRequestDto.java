package com.application.ims.domain.dto.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

}
