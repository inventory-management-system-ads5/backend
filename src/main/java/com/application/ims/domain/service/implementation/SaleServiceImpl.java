package com.application.ims.domain.service.implementation;

import org.springframework.stereotype.Service;
import com.application.ims.domain.service.interfaces.SaleServiceInterface;
import com.application.ims.infrastructure.SaleRepository;
import com.application.ims.infrastructure.CustomerRepository;
import com.application.ims.infrastructure.ProductRepository;
import com.application.ims.infrastructure.SaleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.dto.response.SaleResponseDto;
import com.application.ims.domain.entity.Customer;
import com.application.ims.domain.entity.Sale;
import com.application.ims.domain.enums.SaleStatus;
import com.application.ims.domain.entity.SaleItem;
import com.application.ims.domain.entity.Product;
import com.application.ims.domain.dto.request.create.SaleRequestDto;
import com.application.ims.domain.dto.response.SaleItemResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleServiceInterface {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SaleItemRepository saleItemRepository;

    @Autowired
    public SaleServiceImpl(
            SaleRepository saleRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            SaleItemRepository saleItemRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.saleItemRepository = saleItemRepository;
    }

    // POST method implementation
    @Override
    public SaleResponseDto save(SaleRequestDto saleRequestDto) {

        // fetching the customer with the given id
        Customer customer = customerRepository.findById(saleRequestDto.getCustomer_id()).orElseThrow(()
                -> new RuntimeException("Customer with id "  + saleRequestDto.getCustomer_id() + " not found"));

        // creating a new sale
        Sale sale = new Sale();

        // setting up the new sale base attributes
        sale.setTotalAmount(0.00);
        sale.setCreatedAt(LocalDateTime.now());
        sale.setUpdatedAt(LocalDateTime.now());
        sale.setStatus(SaleStatus.ACTIVE);
        sale.setCustomer(customer);

        Sale savedSale = saleRepository.save(sale);

        // processing sale items
        List<SaleItem> saleItems = saleRequestDto.getSale_items().stream().map(itemDto -> {

            // fetching the product with given id
            Product product = productRepository.findById(itemDto.getProduct_id()).orElseThrow(()
                    -> new RuntimeException("Product with id " + itemDto.getProduct_id() + " not found"));

            // validating the requested quantity vs available quantity
            if (itemDto.getQuantity() > product.getStockQuantity()) {
                throw new RuntimeException("Product with id " + itemDto.getProduct_id() + " doesnt have enough stock");
            }

            // creating a new sale item(s)
            SaleItem saleItem = new SaleItem();

            // setting up the new sale item(s) attributes
            saleItem.setQuantity(itemDto.getQuantity());
            saleItem.setSalePrice(itemDto.getQuantity() * product.getUnitPrice());
            saleItem.setProduct(product);
            saleItem.setSale(savedSale);

            // updating product quantity upon sale item creation
            product.setStockQuantity(product.getStockQuantity() - itemDto.getQuantity()); // removing quantity

            return saleItem;
        }).collect(Collectors.toList());

        saleItemRepository.saveAll(saleItems);

        // calculating sale total amount
        double totalAmount = saleItems.stream().mapToDouble(item -> item.getSalePrice()).sum();

        // setting up sale total amount
        sale.setTotalAmount(totalAmount);

        // updating sale with total amount
        saleRepository.save(savedSale);

        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(savedSale.getId());
        saleResponseDto.setTotal_amount(sale.getTotalAmount());
        saleResponseDto.setCreated_at(savedSale.getCreatedAt());
        saleResponseDto.setUpdated_at(savedSale.getUpdatedAt());
        saleResponseDto.setStatus(savedSale.getStatus());
        saleResponseDto.setCustomer_id(savedSale.getCustomer().getId());

        // mapping a sale item to class dto
        List<SaleItemResponseDto> saleItemResponseDtos = saleItems.stream().map(saleItem -> {
            SaleItemResponseDto saleItemResponseDto = new SaleItemResponseDto();
            saleItemResponseDto.setId(saleItem.getId());
            saleItemResponseDto.setQuantity(saleItem.getQuantity());
            saleItemResponseDto.setSalePrice(saleItem.getSalePrice());
            saleItemResponseDto.setProduct_id(saleItem.getProduct().getId());
            saleItemResponseDto.setSale_id(saleItem.getSale().getId());

            return saleItemResponseDto;

        }).collect(Collectors.toList());

        saleResponseDto.setSale_items(saleItemResponseDtos);

        return saleResponseDto;
    }

    // GET method implementation
    @Override
    public SaleResponseDto getSale(Long id) {

        // fetching the sale with the given id
        Sale sale = saleRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Customer with id "  + id + " not found"));

        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(sale.getId());
        saleResponseDto.setTotal_amount(sale.getTotalAmount());
        saleResponseDto.setCreated_at(sale.getCreatedAt());
        saleResponseDto.setUpdated_at(sale.getUpdatedAt());
        saleResponseDto.setStatus(sale.getStatus());
        saleResponseDto.setCustomer_id(sale.getCustomer().getId());

        // mapping a sale item to class dto
        List<SaleItemResponseDto> saleItemResponseDtos = sale.getSaleItems().stream().map(saleItem -> {
            SaleItemResponseDto saleItemResponseDto = new SaleItemResponseDto();
            saleItemResponseDto.setId(saleItem.getId());
            saleItemResponseDto.setQuantity(saleItem.getQuantity());
            saleItemResponseDto.setSalePrice(saleItem.getSalePrice());
            saleItemResponseDto.setProduct_id(saleItem.getProduct().getId());
            saleItemResponseDto.setSale_id(saleItem.getSale().getId());

            return saleItemResponseDto;

        }).collect(Collectors.toList());

        saleResponseDto.setSale_items(saleItemResponseDtos);

        return saleResponseDto;
    }

    // GET method implementation (list)
    @Override
    public List<SaleResponseDto> getSales() {

        // fetching all existing sales
        List<Sale> sales = saleRepository.findAll();
        return sales.stream().map(this::saleResponseDtos).toList();
    }

    private SaleResponseDto saleResponseDtos(Sale sale) {
        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(sale.getId());
        saleResponseDto.setTotal_amount(sale.getTotalAmount());
        saleResponseDto.setCreated_at(sale.getCreatedAt());
        saleResponseDto.setUpdated_at(sale.getUpdatedAt());
        saleResponseDto.setStatus(sale.getStatus());
        saleResponseDto.setCustomer_id(sale.getCustomer().getId());

        // mapping a sale item to class dto
        List<SaleItemResponseDto> saleItemResponseDtos = sale.getSaleItems().stream().map(saleItem -> {
            SaleItemResponseDto saleItemResponseDto = new SaleItemResponseDto();
            saleItemResponseDto.setId(saleItem.getId());
            saleItemResponseDto.setQuantity(saleItem.getQuantity());
            saleItemResponseDto.setSalePrice(saleItem.getSalePrice());
            saleItemResponseDto.setProduct_id(saleItem.getProduct().getId());
            saleItemResponseDto.setSale_id(saleItem.getSale().getId());

            return saleItemResponseDto;

        }).collect(Collectors.toList());

        saleResponseDto.setSale_items(saleItemResponseDtos);

        return saleResponseDto;
    }

    // PATCH method implementation
    @Override
    public SaleResponseDto updateStatus(Long id) {

        // fetching the sale with the given id
        Sale sale = saleRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Sale with id "  + id + " not found"));

        // validating if the request sale is already cancelled
        if(sale.getStatus() == SaleStatus.CANCELLED) {
            throw new RuntimeException("Sale with id " + id + " is already cancelled");
        }

        // returning product(s) quantity upon sale cancellation
        for (SaleItem saleItem : sale.getSaleItems()) {
            Product product = saleItem.getProduct();
            product.setStockQuantity(product.getStockQuantity() + saleItem.getQuantity()); // adding back quantity
            productRepository.save(product);
        }

        // updating sale status and registering the moment of it
        sale.setUpdatedAt(LocalDateTime.now());
        sale.setStatus(SaleStatus.CANCELLED);

        Sale updatedSale = saleRepository.save(sale);

        SaleResponseDto saleResponseDto = new SaleResponseDto();
        saleResponseDto.setId(updatedSale.getId());
        saleResponseDto.setTotal_amount(updatedSale.getTotalAmount());
        saleResponseDto.setCreated_at(updatedSale.getCreatedAt());
        saleResponseDto.setUpdated_at(updatedSale.getUpdatedAt());
        saleResponseDto.setStatus(updatedSale.getStatus());
        saleResponseDto.setCustomer_id(updatedSale.getCustomer().getId());

        // mapping a sale item to class dto
        List<SaleItemResponseDto> saleItemResponseDtos = sale.getSaleItems().stream().map(saleItem -> {
            SaleItemResponseDto saleItemResponseDto = new SaleItemResponseDto();
            saleItemResponseDto.setId(saleItem.getId());
            saleItemResponseDto.setQuantity(saleItem.getQuantity());
            saleItemResponseDto.setSalePrice(saleItem.getSalePrice());
            saleItemResponseDto.setProduct_id(saleItem.getProduct().getId());
            saleItemResponseDto.setSale_id(saleItem.getSale().getId());

            return saleItemResponseDto;

        }).collect(Collectors.toList());

        saleResponseDto.setSale_items(saleItemResponseDtos);

        return saleResponseDto;
    }
    
}
