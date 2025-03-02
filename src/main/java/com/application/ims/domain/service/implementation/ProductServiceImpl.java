package com.application.ims.domain.service.implementation;

import org.springframework.stereotype.Service;
import com.application.ims.domain.service.interfaces.ProductServiceInterface;
import com.application.ims.infrastructure.ProductRepository;
import com.application.ims.infrastructure.CategoryRepository;
import com.application.ims.infrastructure.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.dto.response.ProductResponseDto;
import com.application.ims.domain.dto.request.create.ProductRequestDto;
import com.application.ims.domain.entity.Category;
import com.application.ims.domain.entity.Supplier;
import com.application.ims.domain.entity.Product;
import com.application.ims.domain.dto.request.update.UpdateProductRequestDto;
import com.application.ims.domain.dto.request.update.AddProductStockRequestDto;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            SupplierRepository supplierRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    // POST method implementation
    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) {

        // fetching the category with the given id
        Category category = categoryRepository.findById(productRequestDto.getCategory_id()).orElseThrow(()
                -> new RuntimeException("Category with id " + productRequestDto.getCategory_id() + " not found"));

        // fetching the supplier with the given id
        Supplier supplier = supplierRepository.findById(productRequestDto.getSupplier_id()).orElseThrow(()
                -> new RuntimeException("Supplier with id " + productRequestDto.getSupplier_id() + " not found"));

        // creating a new product
        Product product = new Product();

        // setting up the new product attributes
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setStockQuantity(productRequestDto.getStock_quantity());
        product.setUnitPrice(productRequestDto.getUnit_price());
        product.setCategory(category);
        product.setSupplier(supplier);

        Product savedProduct = productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(savedProduct.getId());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setDescription(savedProduct.getDescription());
        productResponseDto.setStock_quantity(savedProduct.getStockQuantity());
        productResponseDto.setUnit_price(savedProduct.getUnitPrice());
        productResponseDto.setCategory_id(savedProduct.getCategory().getId());
        productResponseDto.setSupplier_id(savedProduct.getSupplier().getId());

        return productResponseDto;
    }

    // GET method implementation
    @Override
    public ProductResponseDto getProduct(Long id) {

        // fetching the product with the given id
        Product product = productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Product with id " + id + " not found"));


        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setStock_quantity(product.getStockQuantity());
        productResponseDto.setUnit_price(product.getUnitPrice());
        productResponseDto.setCategory_id(product.getCategory().getId());
        productResponseDto.setSupplier_id(product.getSupplier().getId());

        return productResponseDto;
    }

    // GET method implementation (list)
    @Override
    public List<ProductResponseDto> getProducts() {

        // fetching all existing products
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::ProductResponseDtos).toList();

    }

    private ProductResponseDto ProductResponseDtos(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setStock_quantity(product.getStockQuantity());
        productResponseDto.setUnit_price(product.getUnitPrice());
        productResponseDto.setCategory_id(product.getCategory().getId());
        productResponseDto.setSupplier_id(product.getSupplier().getId());

        return productResponseDto;
    }

    // PUT method implementation
    @Override
    public ProductResponseDto update(Long id, UpdateProductRequestDto productRequestDto) {

        // fetching the product with the given id
        Product product = productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Product with id " + id + " not found"));

        // fetching the category with the given id
        Category category = categoryRepository.findById(productRequestDto.getCategory_id()).orElseThrow(()
                -> new RuntimeException("Category with id " + productRequestDto.getCategory_id() + " not found"));

        // fetching the supplier with the given id
        Supplier supplier = supplierRepository.findById(productRequestDto.getSupplier_id()).orElseThrow(()
                -> new RuntimeException("Supplier with id " + productRequestDto.getSupplier_id() + " not found"));


        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setStockQuantity(productRequestDto.getStock_quantity());
        product.setUnitPrice(productRequestDto.getUnit_price());
        product.setCategory(category);
        product.setSupplier(supplier);

        Product updatedProduct = productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(updatedProduct.getId());
        productResponseDto.setName(updatedProduct.getName());
        productResponseDto.setDescription(updatedProduct.getDescription());
        productResponseDto.setStock_quantity(updatedProduct.getStockQuantity());
        productResponseDto.setUnit_price(updatedProduct.getUnitPrice());
        productResponseDto.setCategory_id(updatedProduct.getCategory().getId());
        productResponseDto.setSupplier_id(updatedProduct.getSupplier().getId());

        return productResponseDto;
    }

    // PATCH method implementation
    @Override
    public ProductResponseDto updateStock(Long id, AddProductStockRequestDto productRequestDto) {

        // fetching the product with the given id
        Product product = productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Product with id " + id + " not found"));

        product.setStockQuantity(product.getStockQuantity() + productRequestDto.getStock_quantity());

        // updating the product quantity
        Product updatedProduct = productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(updatedProduct.getId());
        productResponseDto.setName(updatedProduct.getName());
        productResponseDto.setDescription(updatedProduct.getDescription());
        productResponseDto.setStock_quantity(updatedProduct.getStockQuantity());
        productResponseDto.setUnit_price(updatedProduct.getUnitPrice());
        productResponseDto.setCategory_id(updatedProduct.getCategory().getId());
        productResponseDto.setSupplier_id(updatedProduct.getSupplier().getId());

        return productResponseDto;
    }

    // DELETE method implementation
    @Override
    public ProductResponseDto delete(Long id) {

        // fetching the product with the given id
        Product product = productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Product with id " + id + " not found"));

        productRepository.delete(product);

        return null;
    }
}
