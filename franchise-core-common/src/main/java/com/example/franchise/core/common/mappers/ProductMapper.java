package com.example.franchise.core.common.mappers;

import com.example.franchise.core.common.responses.ProductDto;
import com.example.franchise.core.domain.model.ProductRecord;

public class ProductMapper {
    private ProductMapper() {
        throw new IllegalStateException("ProductMapper");
    }

    public static ProductDto mapToDto(ProductRecord record) {
        ProductDto dto = new ProductDto();
        dto.setName(record.getName());
        dto.setStockQuantity(record.getStockQuantity());
        return dto;
    }
}
