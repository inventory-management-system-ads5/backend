package com.application.ims.infrastructure;

import com.application.ims.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    // querying all customers by id ascending
    @Query("SELECT cu FROM Customer cu ORDER BY cu.id ASC")
    List<Customer> findAllByIdAsc();


}
