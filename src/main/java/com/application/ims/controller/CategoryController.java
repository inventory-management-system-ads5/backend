package com.application.ims.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.service.interfaces.CategoryServiceInterface;
import com.application.ims.domain.dto.response.CategoryResponseDto;
import com.application.ims.domain.dto.request.create.CategoryRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCategoryRequestDto;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    private final CategoryServiceInterface categoryService;

    @Autowired
    public CategoryController(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }

    // POST method
    @PostMapping("/add/") // http://localhost:8080/api/category/add/
    public CategoryResponseDto save(
            @RequestBody CategoryRequestDto categoryRequestDto
    ) {
        return categoryService.save(categoryRequestDto);
    }

    // GET method (category per id)
    @GetMapping("/{id}/") // http://localhost:8080/api/category/{id}/
    public CategoryResponseDto getCategory(
            @PathVariable Long id
    ) {
        return categoryService.getCategory(id);
    }

    // GET method (all categories)
    @GetMapping // http://localhost:8080/api/category/
    public List<CategoryResponseDto> getCategories() {
        return categoryService.getCategories();
    }

    // PUT method
    @PutMapping("/{id}/update/") // http://localhost:8080/api/category/{id}/update/
    public CategoryResponseDto update(
            @PathVariable Long id,
            @RequestBody UpdateCategoryRequestDto categoryRequestDto
    ) {
        return categoryService.update(id, categoryRequestDto);
    }

    // DELETE method
    @DeleteMapping("/{id}/delete/") // http://localhost:8080/api/category/{id}/delete/
    public CategoryResponseDto delete(
            @PathVariable Long id
    ) {
        return categoryService.delete(id);
    }

}
