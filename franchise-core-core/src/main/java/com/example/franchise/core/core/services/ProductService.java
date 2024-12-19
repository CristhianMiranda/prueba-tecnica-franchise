package com.example.franchise.core.core.services;

import com.example.franchise.core.common.constants.ErrorMessages;
import com.example.franchise.core.common.mappers.ProductMapper;
import com.example.franchise.core.common.responses.ProductDto;
import com.example.franchise.core.core.components.I18NComponent;
import com.example.franchise.core.core.exceptions.ApiException;
import com.example.franchise.core.domain.dto.ProductTopDto;
import com.example.franchise.core.domain.model.ProductRecord;
import com.example.franchise.core.domain.repository.BranchRepository;
import com.example.franchise.core.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final I18NComponent i18NService;
    private final ProductRepository repository;
    private final BranchRepository branchRepository;

    public ProductService(I18NComponent i18NService, ProductRepository repository, BranchRepository branchRepository) {
        this.i18NService = i18NService;
        this.repository = repository;
        this.branchRepository = branchRepository;
    }

    @Transactional(rollbackOn = ApiException.class)
    public ProductDto create(String name, int stock, Long branchId)  {
        log.info("ProductService::create --name:[{}], --stock:[{}], --branchId:[{}]",name, stock, branchId);
        if(repository.existsByName(name)){
            throw new ApiException(i18NService.getMessage(ErrorMessages.ERROR_PRODUCT_EXIST_NAME));
        }
        ProductRecord entity = new ProductRecord();
        entity.setName(name);
        entity.setStockQuantity(stock);
        entity.setBranch(branchRepository.findById(branchId).get());
        return ProductMapper.mapToDto(repository.save(entity));
    }

    @Transactional(rollbackOn = ApiException.class)
    public ProductDto updateName(Long id, String requestName)  {
        log.info("ProductService::updateName --id:[{}], --requestName:[{}]",id,requestName);
        if(repository.existsByNameAndIdNot(requestName,id)){
            throw new ApiException(i18NService.getMessage(ErrorMessages.ERROR_UPDATE_PRODUCT_EXIST_NAME));
        }
        ProductRecord entity = getById(id);
        entity.setName(requestName);
        return ProductMapper.mapToDto(repository.save(entity));
    }

    @Transactional(rollbackOn = ApiException.class)
    public ProductDto updateStock(Long id, int requestStock)  {
        log.info("ProductService::updateName --id:[{}], --requestStock:[{}]",id,requestStock);
        ProductRecord entity = getById(id);
        entity.setStockQuantity(requestStock);
        return ProductMapper.mapToDto(repository.save(entity));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public ProductRecord getById(Long id) {
        log.info("ProductService::getById --id:[{}]",id);
        return repository.findById(id).orElseThrow(()-> new ApiException(i18NService.getMessage(ErrorMessages.ERROR_PRODUCT_NOT_FOUND)));
    }

    public List<ProductTopDto> getAllTopByFranchiseId(Long franchiseId) {
        log.info("BranchService::getAllTopByFranchiseId --franchiseId:[{}]",franchiseId);
        return repository.getAllTopByFranchiseId(franchiseId);
    }

}
