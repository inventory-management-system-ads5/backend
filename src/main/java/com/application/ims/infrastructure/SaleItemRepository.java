package com.application.ims.infrastructure;

import com.application.ims.domain.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    // querying all sale items by id ascending
    @Query("SELECT sali FROM SaleItem sali ORDER BY sali.id ASC")
    List<SaleItem> findAllByIdAsc();

}
