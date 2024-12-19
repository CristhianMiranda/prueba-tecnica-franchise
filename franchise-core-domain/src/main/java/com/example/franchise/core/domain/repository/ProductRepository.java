package com.example.franchise.core.domain.repository;

import com.example.franchise.core.domain.dto.ProductTopDto;
import com.example.franchise.core.domain.model.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductRecord, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);

    @Query("SELECT new com.example.franchise.core.domain.dto.ProductTopDto(p.id, p.name, p.stockQuantity, b.id, b.name) " +
            "FROM FranchiseRecord f " +
            "JOIN f.branches b " +
            "JOIN b.products p " +
            "WHERE f.id = :franchiseId AND p.stockQuantity = (SELECT MAX(p2.stockQuantity) FROM ProductRecord p2 WHERE p2.branch.id = b.id)")
    List<ProductTopDto> getAllTopByFranchiseId(Long franchiseId);
}
