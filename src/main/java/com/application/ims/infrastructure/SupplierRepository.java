package com.application.ims.infrastructure;

import com.application.ims.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // querying all users by id ascending
    @Query("SELECT sup FROM Supplier sup ORDER BY sup.id ASC")
    List<Supplier> findAllByIdAsc();

}
