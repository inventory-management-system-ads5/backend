package com.application.ims.infrastructure;

import com.application.ims.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustumerRepository extends JpaRepository<Customer, Long> {
}
