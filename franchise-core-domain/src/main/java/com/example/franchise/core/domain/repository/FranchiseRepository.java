package com.example.franchise.core.domain.repository;

import com.example.franchise.core.domain.model.FranchiseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<FranchiseRecord, Long> {
   boolean existsByName(String name);
   boolean existsByNameAndIdNot(String name, Long id);
}
