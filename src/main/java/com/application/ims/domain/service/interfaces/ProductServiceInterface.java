package com.application.ims.domain.service.interfaces;

import com.application.ims.domain.dto.request.create.ProductRequestDto;
import com.application.ims.domain.dto.request.update.AddProductStockRequestDto;
import com.application.ims.domain.dto.request.update.UpdateProductRequestDto;
import com.application.ims.domain.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductServiceInterface {
    ProductResponseDto save(ProductRequestDto productRequestDto);
    ProductResponseDto getProduct(Long id);
    List<ProductResponseDto> getProducts();
    ProductResponseDto update(Long id, UpdateProductRequestDto productRequestDto);
    ProductResponseDto updateStock(Long id, AddProductStockRequestDto productRequestDto);
    ProductResponseDto delete(Long id);

}
