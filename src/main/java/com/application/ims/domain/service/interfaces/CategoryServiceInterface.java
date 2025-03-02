package com.application.ims.domain.service.interfaces;

import com.application.ims.domain.dto.request.create.CategoryRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCategoryRequestDto;
import com.application.ims.domain.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryServiceInterface {
    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategory(Long id);
    List<CategoryResponseDto> getCategorys();
    CategoryResponseDto update(Long id, UpdateCategoryRequestDto categoryRequestDto);
    CategoryResponseDto delete(Long id);
}
