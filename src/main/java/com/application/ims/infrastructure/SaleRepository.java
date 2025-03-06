package com.application.ims.infrastructure;

import com.application.ims.domain.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    // querying all sales by id ascending
    @Query("SELECT sal FROM Sale sal ORDER BY sal.id ASC")
    List<Sale> findAllByIdAsc();

}
