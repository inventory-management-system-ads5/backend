package com.application.ims.infrastructure;

import com.application.ims.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // querying all categories by id ascending
    @Query("SELECT cat FROM Category cat ORDER BY cat.id ASC")
    List<Category> findAllByIdAsc();

}
