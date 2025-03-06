package com.application.ims.infrastructure;

import com.application.ims.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // querying all products by id ascending
    @Query("SELECT pro FROM Product pro ORDER BY pro.id ASC")
    List<Product> findAllByIdAsc();

}
