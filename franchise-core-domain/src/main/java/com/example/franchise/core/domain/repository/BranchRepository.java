package com.example.franchise.core.domain.repository;

import com.example.franchise.core.domain.dto.ProductTopDto;
import com.example.franchise.core.domain.model.BranchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<BranchRecord, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String requestName, Long id);
}
