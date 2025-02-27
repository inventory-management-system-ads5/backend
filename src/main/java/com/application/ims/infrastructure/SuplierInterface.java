package com.application.ims.infrastructure;

import com.application.ims.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierInterface extends JpaRepository<Supplier, Long> {
}
