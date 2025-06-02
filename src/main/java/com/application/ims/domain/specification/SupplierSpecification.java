package com.application.ims.domain.specification;

import com.application.ims.domain.entity.Supplier;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SupplierSpecification {

    public static Specification<Supplier> getSuppliersByCriteria(String searchTerm, Boolean isActive) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // search term for name and contactInfo
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                String likePattern = "%" + searchTerm.toLowerCase().trim() + "%";
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern);
                Predicate contactInfoPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("contactInfo")), likePattern);
                predicates.add(criteriaBuilder.or(namePredicate, contactInfoPredicate));
            }

            // filter by isActive status
            if (isActive != null) {
                predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
            }

            // ensuring results are still ordered by id
            query.orderBy(criteriaBuilder.asc(root.get("id")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
