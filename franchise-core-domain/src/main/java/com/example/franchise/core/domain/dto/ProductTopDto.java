package com.example.franchise.core.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductTopDto {
    private Long id;
    private String name;
    private int stock;
    private Long branchId;
    private String branchName;

    public ProductTopDto(Long id, String name, int stock, Long branchId, String branchName) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.branchId = branchId;
        this.branchName = branchName;
    }
}
