package com.application.ims.domain.service.interfaces;

import com.application.ims.domain.dto.request.create.CategoryRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCategoryRequestDto;
import com.application.ims.domain.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryServiceInterface {
    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategory(Long id);
    List<CategoryResponseDto> getCategories();
    CategoryResponseDto update(Long id, UpdateCategoryRequestDto categoryRequestDto);
    CategoryResponseDto delete(Long id);

}
