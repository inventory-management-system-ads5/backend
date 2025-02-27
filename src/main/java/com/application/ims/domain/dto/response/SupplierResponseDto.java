package com.application.ims.domain.dto.response;

import com.application.ims.domain.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String contact_info;

//    @NotNull
//    private boolean isActive;

//    @NotNull
//    private List<Product> products;

}
