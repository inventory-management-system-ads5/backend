package com.application.ims.domain.service.implementation;

import org.springframework.stereotype.Service;
import com.application.ims.domain.service.interfaces.CategoryServiceInterface;
import com.application.ims.infrastructure.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.dto.response.CategoryResponseDto;
import com.application.ims.domain.entity.Category;
import com.application.ims.domain.dto.request.create.CategoryRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCategoryRequestDto;

import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryServiceInterface {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    // POST method implementation
    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {

        // creating up a new category
        Category category = new Category();

        // setting up the new category attributes
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());

        Category savedCategory = categoryRepository.save(category);

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(savedCategory.getId());
        categoryResponseDto.setName(savedCategory.getName());
        categoryResponseDto.setDescription(savedCategory.getDescription());

        return categoryResponseDto;
    }

    // GET method implementation
    @Override
    public CategoryResponseDto getCategory(Long id) {

        // fetching the category with the given id
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Category with id " + id + " not found"));

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDescription(category.getDescription());

        return categoryResponseDto;
    }

    // GET method implementation (list)
    @Override
    public List<CategoryResponseDto> getCategories() {

        // fetching all existing categories
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::categoryResponseDtos).toList();
    }

    private CategoryResponseDto categoryResponseDtos(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDescription(category.getDescription());

        return categoryResponseDto;
    }

    // PUT method implementation
    @Override
    public CategoryResponseDto update(Long id, UpdateCategoryRequestDto categoryRequestDto) {

        // fetching the category with the given id
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Category with id " + id + " not found"));

        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());

        // updating the category with new given data
        Category updatedCategory = categoryRepository.save(category);

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(updatedCategory.getId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDescription(category.getDescription());

        return categoryResponseDto;
    }

    // DELETE method implementation
    @Override
    public CategoryResponseDto delete(Long id) {

        // fetching the category with the given id
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Category with id " + id + " not found"));

        categoryRepository.delete(category);

        return null;
    }
}
